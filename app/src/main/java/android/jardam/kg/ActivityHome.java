package android.jardam.kg;

import android.app.Dialog;
import android.content.Intent;
import android.jardam.kg.connection.JNet;
import android.jardam.kg.connection.entity.Ad;
import android.jardam.kg.connection.entity.GetAds;
import android.jardam.kg.connection.entity.Human;
import android.jardam.kg.dialogs.App;
import android.jardam.kg.dialogs.TransferDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "ActivityHome";

    ImageView imageHuman;
    TextView textHumanDesc;

    ConstraintLayout layoutNoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        WebView webViewLogin = findViewById(R.id.webViewNews);
        webViewLogin.loadUrl("http://jardam.urmapps.com/");

        findViewById(R.id.buttonTransfer).setOnClickListener(this);
        findViewById(R.id.layoutHuman).setOnClickListener(this);
        layoutNoConnection = findViewById(R.id.layoutNoInternet);
        imageHuman = findViewById(R.id.imageHuman);
        textHumanDesc = findViewById(R.id.textHumanDesc);

        getHumanData();

        if (!App.isOnline(this))
            layoutNoConnection.setVisibility(View.VISIBLE);
    }

    private void getHumanData() {
        if (App.isOnline(this))
            JNet.getAd(new Callback<GetAds>() {
                @Override
                public void onResponse(Call<GetAds> call, Response<GetAds> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        GetAds getAd = response.body();
                        if (getAd.ad != null) {
                            Ad ad = getAd.ad;
                            textHumanDesc.setText(ad.subject + "\n" + ad.description);
                            Glide.with(ActivityHome.this)
                                    .load(ad.image)
                                    .into(imageHuman);
                        }else Log.i(TAG, "onResponse: ads is null or empty");
                    } else {
                        try {
                            Log.i(TAG, "onResponse: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetAds> call, Throwable t) {
                    t.printStackTrace();
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeMShare:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, "http://jardam.urmapps.com/");
                startActivity(Intent.createChooser(i, getString(R.string.shareLink)));
                break;
            case R.id.homeMPrivate:
                startActivity(new Intent(this, ActivityPrivate.class));
                break;

            case R.id.homeMSettings:
                startActivity(new Intent(this, ActivitySettings.class));
                break;
            case R.id.homeMRequisites:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_requisits);
                dialog.setTitle(getString(R.string.fundRequisites));
                dialog.findViewById(R.id.buttonDialogReq).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        }
                );
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonTransfer:
                final TransferDialog dialog = new TransferDialog(this);
                dialog.setContentView(R.layout.dialog_transfer);
                dialog.setTitle(getString(R.string.acceptTransfer));
                dialog.show();
                break;
            case R.id.layoutHuman:
                startActivity(new Intent(this, ActivityAboutHuman.class));
                break;
        }
    }
}
