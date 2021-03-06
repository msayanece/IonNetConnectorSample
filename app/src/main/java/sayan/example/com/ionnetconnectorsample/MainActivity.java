package sayan.example.com.ionnetconnectorsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadImageLong(View view) {
        startActivity(new Intent(this,ImageLongActivity.class));
    }

    public void loadImage(View view) {
        startActivity(new Intent(this,ImageActivity.class));
    }

    public void downloadWithPB(View view) {
        startActivity(new Intent(this,DownloadWithProgressBar.class));
    }

    public void loadJSON(View view) {
        startActivity(new Intent(this,GetJSONData.class));
    }
}
