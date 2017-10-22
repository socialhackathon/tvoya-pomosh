package android.jardam.kg.dialogs;

import android.content.Context;
import android.content.Intent;
import android.jardam.kg.AppSettings;
import android.jardam.kg.AppValues;
import android.jardam.kg.R;
import android.jardam.kg.connection.JNet;
import android.jardam.kg.connection.entity.GetSum;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.method.Touch;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferDialog extends AppCompatDialog implements View.OnClickListener {

    public String title = "Подтвердите перевод";
    private Context context;
    NumberPicker numberPCount;
    ProgressBar progressBar;

    public TransferDialog(Context context) {
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

        progressBar = findViewById(R.id.progressBarTransfer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDialogCancel:
                dismiss();
                break;
            case R.id.buttonDialogOk:
                progressBar.setVisibility(View.VISIBLE);
                JNet.addOperation(new Callback<GetSum>() {
                                      @Override
                                      public void onResponse(Call<GetSum> call, Response<GetSum> response) {
                                          if(response.isSuccessful() && response.body() != null){
                                              AppSettings.putInt(context, AppValues.SUM, response.body().summ.sum);
                                              Toast.makeText(context, context.getString(R.string.youTransfered)
                                                      + response.body().summ.sum
                                                      + " " + context.getString(R.string.som)
                                                      , Toast.LENGTH_LONG).show();
                                          }else {
                                              Toast.makeText(context, context.getString(R.string.anyError)
                                                      , Toast.LENGTH_SHORT).show();
                                              try {
                                                  Log.i("TransferDialog", "onResponse: " + response.errorBody().string());
                                              } catch (IOException e) {
                                                  e.printStackTrace();
                                              }
                                          }
                                          dismiss();
                                          progressBar.setVisibility(View.GONE);
                                      }

                                      @Override
                                      public void onFailure(Call<GetSum> call, Throwable t) {
                                          Toast.makeText(context, context.getString(R.string.anyError)
                                                  , Toast.LENGTH_SHORT).show();
                                          progressBar.setVisibility(View.GONE);
                                          t.printStackTrace();
                                          dismiss();
                                      }
                                  },
                        AppSettings.getInt(context, AppValues.ADD_ID),
                        AppSettings.getInt(context, AppValues.USER_ID),
                        numberPCount.getValue()
                );
//                String cToSend = "tel:" + Uri.encode("*") + "100" + Uri.encode("#");
//                context.startActivity(new Intent("android.intent.action.CALL",
//                        Uri.parse(cToSend)), null);
//                dismiss();
                break;
        }
    }
}
