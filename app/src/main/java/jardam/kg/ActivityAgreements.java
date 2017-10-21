package jardam.kg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityAgreements extends AppActivity {

    Button buttonAree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreements);
        buttonAree = (Button) findViewById(R.id.buttonAgree);
        buttonAree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAgree:
                startActivity(new Intent(this, ActivityRegistration.class));
                finish();
                break;
        }
    }
}
