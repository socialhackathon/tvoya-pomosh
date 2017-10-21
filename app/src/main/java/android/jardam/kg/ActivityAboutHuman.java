package android.jardam.kg;

import android.jardam.kg.connection.JNet;
import android.jardam.kg.connection.entity.GetAds;
import android.jardam.kg.connection.entity.Human;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAboutHuman extends AppCompatActivity {

    ImageView imageHuman;
    TextView textFio;
    TextView textAge;
    TextView textStatus;
    TextView textAddress;
    TextView textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_human);
        imageHuman = findViewById(R.id.imageHuman);
        textFio = findViewById(R.id.textHumanFio);
        textAge = findViewById(R.id.textHumanAge);
        textStatus = findViewById(R.id.textHumanStatus);
        textAddress = findViewById(R.id.textHumanAddress);
        textDesc = findViewById(R.id.textHumanDesc);
        getHumanData();
    }

    private void getHumanData(){
        JNet.getAd(new Callback<GetAds>() {
            @Override
            public void onResponse(Call<GetAds> call, Response<GetAds> response) {
                if(response.isSuccessful() && response.body() != null){
                    GetAds getAds = response.body();
                    Glide.with(ActivityAboutHuman.this)
                            .load(getAds.ad.image)
                            .into(imageHuman);
                    textFio.setText(getAds.ad.subject);
                    textDesc.setText(getAds.ad.description);
//                    textAge.setText("");
//                    textStatus.setText("");
//                    textAddress.setText("");
                }
            }

            @Override
            public void onFailure(Call<GetAds> call, Throwable t) {
                textDesc.setText(t.getMessage());
            }
        });
    }
}
