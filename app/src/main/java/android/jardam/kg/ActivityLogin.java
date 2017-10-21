package android.jardam.kg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.jardam.kg.connection.JNet;
import android.jardam.kg.connection.entity.AddUser;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ActivityLogin";

    EditText editName, editLogin;
    ProgressBar mProgressBar;

    String mName, mLogin, mDeviceID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebView webViewLogin = (WebView) findViewById(R.id.webViewLogin);
        webViewLogin.loadUrl("http://jardam.urmapps.com/");

        editName = (EditText) findViewById(R.id.editName);
        editLogin = (EditText) findViewById(R.id.editLogin);
        mProgressBar = findViewById(R.id.progressBarLogin);

        mDeviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i("ActivityLogin", "onCreate: " + mDeviceID);
        findViewById(R.id.buttonSave).setOnClickListener(this);
        findViewById(R.id.buttonSkip).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                mName = editName.getText().toString();
                mLogin = editLogin.getText().toString();
                addUser();
                break;
            case R.id.buttonSkip:
                addUser();
                break;
        }
    }

    private void addUser(){
        mProgressBar.setVisibility(View.VISIBLE);
        JNet.addUser(
                new Callback<AddUser>() {
                    @Override
                    public void onResponse(Call<AddUser> call, Response<AddUser> response) {
                        if (response != null && response.isSuccessful()
                                && response.body() != null) {

                            AddUser addUser = response.body();
                            if (addUser.status != "Error" && addUser.userId > 0) {
                                AppSettings.putString(ActivityLogin.this, AppValues.DEV_ID,
                                        mDeviceID);
                                AppSettings.putString(ActivityLogin.this, AppValues.NAME,
                                        mName);
                                AppSettings.putString(ActivityLogin.this, AppValues.LOGIN,
                                        mLogin);
                                AppSettings.putBoolean(ActivityLogin.this, AppValues.IS_LOGIN, true);
                                AppSettings.putInt(ActivityLogin.this, AppValues.USER_ID, addUser.userId);
                                mProgressBar.setVisibility(View.GONE);
                                toHome();
                            }
                        }else {
                            try {
                                Log.i(TAG, "onResponse: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddUser> call, Throwable t) {
                        t.printStackTrace();
                    }
                },
                mName != "" ? mName : "0",
                "0",
                "0",
                mLogin != "" ? mLogin : "0",
                mDeviceID);
    }

    private void toHome() {
        startActivity(new Intent(this, ActivityHome.class));
        finish();
    }
}
