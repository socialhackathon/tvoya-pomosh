package android.jardam.kg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {

    EditText editDefault;
    ImageView imageHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.layoutHuman).setOnClickListener(this);
        findViewById(R.id.buttonSaveSettings).setOnClickListener(this);
        editDefault = (EditText) findViewById(R.id.editDefValue);
        editDefault.setText("" + AppSettings.getInt(this, AppValues.DEF_VALUE));

        imageHuman = findViewById(R.id.imageHuman);

        Glide.with(this).load("http://jardam.urmapps.com/user.png").into(imageHuman);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutHuman:
                startActivity(new Intent(this, ActivityAboutHuman.class));
                break;
            case R.id.buttonSaveSettings:
                AppSettings.putInt(this, AppValues.DEF_VALUE,
                        Integer.parseInt(editDefault.getText().toString()));
                finish();
                break;
        }
    }
}
