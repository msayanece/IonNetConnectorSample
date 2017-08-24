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

public class GetJSONData extends AppCompatActivity {

    TextView textView;

    Gson gsonObject;
    UserDetails pojo;
    JsonObject addressJsonObject;
    JsonArray phoneNumbersJsonArray;


    /* the follwong JSON will be returned when Ion library hit the URL...

     {
    "firstName": "John",
            "lastName": "Smith",
            "gender": "man",
            "age": 32,
            "address": {
        "streetAddress": "21 2nd Street",
                "city": "New York",
                "state": "NY",
                "postalCode": "10021"
    },
            "phoneNumbers": [
    { "type": "home", "number": "212 555-1234" },
    { "type": "fax", "number": "646 555-4567" }
    ]
}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JsonObject result = null;
        setContentView(R.layout.activity_get_jsondata);
        String url = "https://api.myjson.com/bins/imon9";
        try {
            result = getJSON(this, url, createProgressDialog());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        textView = (TextView) findViewById(R.id.textView);
        assert result != null;
        gsonProcessResultJsonObject(result);
        postProcessResultJsonElements();
    }

    //get JSON Objects and Arrays
    private void gsonProcessResultJsonObject(JsonObject result) {
        gsonObject = new Gson();
        pojo = gsonObject.fromJson(result, UserDetails.class);              //The UserDetails (java) Object from the main JsonObject
        addressJsonObject = pojo.getAddress();                              //Address details JsonObject within the main JsonObject
        phoneNumbersJsonArray = pojo.getPhoneNumbers();                     //Phone Number details JsonArray within the main JsonObject
    }


    // using the json data set the text filed in the TextView (Post Processing)
    private void postProcessResultJsonElements() {

        textView.setText("Name: "+pojo.getFirstName());
        textView.append(" ");
        textView.append(pojo.getLastName());
        textView.append("\n");
        textView.append("Age: " + pojo.getAge());
        textView.append("\n");
        textView.append("Gender: " + pojo.getGender());
        textView.append("\n");

        //The UserAddress (java) Object from the addressJsonObject
        UserAddress address = gsonObject.fromJson(addressJsonObject, UserAddress.class);
        textView.append("\n");
        textView.append("Address");
        textView.append("\n");
        textView.append("\n");
        textView.append("City: " + address.getCity());
        textView.append("\n");
        textView.append("PIN: " + address.getPostalCode());
        textView.append("\n");
        textView.append("State: " + address.getState());
        textView.append("\n");
        textView.append("Street: " + address.getStreetAddress());
        textView.append("\n");

        //The PhoneNumbers ArrayList of JsonObject from phoneNumbersJsonArray
        ArrayList<JsonObject> phoneNumbers = gsonObject.fromJson(phoneNumbersJsonArray, new TypeToken<ArrayList<JsonObject>>(){}.getType());
        textView.append("\n");
        textView.append("Phone Numbers");
        textView.append("\n");
        textView.append("\n");
        for (JsonObject phoneNumber: phoneNumbers) {

            //The PhoneNumber (java) Object from the phoneNumber JsonObject
            PhoneNumber phone = gsonObject.fromJson(phoneNumber, PhoneNumber.class);
            textView.append(phone.getType());
            textView.append(" - ");
            textView.append(phone.getNumber());
            textView.append("\n");
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

    //get the JsonObject from the server using Ion Library
    private JsonObject getJSON(final Context context, String url, final ProgressDialog dialog) throws ExecutionException, InterruptedException {
        dialog.show();
        Future<JsonObject> resultJSON = Ion.with(context)
                .load(url)
                .progressDialog(dialog)
                .setLogging("sayanJson", Log.DEBUG)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Toast.makeText(context, "JSON Loaded Successfully!", Toast.LENGTH_SHORT).show();
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });
        return resultJSON.get();
    }
}
