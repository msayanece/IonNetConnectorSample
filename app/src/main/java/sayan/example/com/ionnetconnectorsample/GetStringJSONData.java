package sayan.example.com.ionnetconnectorsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import sayan.example.com.ionnetconnectorsample.pojos.PhoneNumber;
import sayan.example.com.ionnetconnectorsample.pojos.UserAddress;
import sayan.example.com.ionnetconnectorsample.pojos.UserDetails;

public class GetStringJSONData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String result = null;
        setContentView(R.layout.activity_get_jsondata);
        String url = "https://api.myjson.com/bins/imon9";
        try {
            result = getJSON(this, url, createProgressDialog());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //create the progressDialog object
    private ProgressDialog createProgressDialog(){
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Loading...");
        dlg.setIndeterminate(false);                            //indeterminate= circular progress
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dlg;
    }

    private String getJSON(final Context context, String url, final ProgressDialog dialog) throws ExecutionException, InterruptedException {
        dialog.show();
        Future<String> resultJSON = Ion.with(context)
                .load(url)
                .progressDialog(dialog)
                .setLogging("sayanJson", Log.DEBUG)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });
        return resultJSON.get();
    }
}
