package android.jardam.kg.dialogs;

import android.content.Context;
import android.content.Intent;
import android.jardam.kg.AppSettings;
import android.jardam.kg.AppValues;
import android.jardam.kg.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.NumberPicker;

public class TransferDialog extends AppCompatDialog implements View.OnClickListener {

    public String title = "Подтвердите перевод";
    private Context context;
    NumberPicker numberPCount;

    public TransferDialog(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_transfer);
        setTitle(title);
        findViewById(R.id.buttonDialogCancel).setOnClickListener(this);
        findViewById(R.id.buttonDialogOk).setOnClickListener(this);

        numberPCount = (NumberPicker) findViewById(R.id.numberPCount);
        numberPCount.setMinValue(1);
        numberPCount.setMaxValue(10000);
        numberPCount.setValue(AppSettings.getInt(context, AppValues.DEF_VALUE));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonDialogCancel:
                dismiss();
                break;
            case R.id.buttonDialogOk:
                String cToSend = "tel:" + Uri.encode("*") + "100" + Uri.encode("#");
                context.startActivity(new Intent("android.intent.action.CALL",
                        Uri.parse(cToSend)), null);
                dismiss();
                break;
        }
    }
}
