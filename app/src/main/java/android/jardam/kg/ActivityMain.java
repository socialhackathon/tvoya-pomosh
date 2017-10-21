package android.jardam.kg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonAgree).setOnClickListener(this);
        nextActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAgree:
                AppSettings.putBoolean(this, "isUserAgree", true);
                nextActivity();
                break;
        }
    }

    private void nextActivity() {
        if (AppSettings.getBoolean(this, "isUserAgree")) {
            if (AppSettings.getBoolean(this, AppValues.IS_LOGIN)) {
                startActivity(new Intent(this, ActivityHome.class));
            } else {
                startActivity(new Intent(this, ActivityLogin.class));
            }
            finish();
        }
    }
}
