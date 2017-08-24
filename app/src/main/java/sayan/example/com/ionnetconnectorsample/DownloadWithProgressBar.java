package sayan.example.com.ionnetconnectorsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

public class DownloadWithProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_with_progress_bar);

        String fileURL = "http://www.oed.com/staticfiles/oedtoday2/oxford_and_the_dictionary.pdf";
        download(this, fileURL, createProgressBar(), createProgressDialog());       //use progressBar or progressDialog any one
    }

    //create the progress bar object
    private ProgressBar createProgressBar(){
        return (ProgressBar) findViewById(R.id.indeterminateBar);
    }

    //create the progressDialog object
    private ProgressDialog createProgressDialog(){
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Loading...");
        dlg.setIndeterminate(false);                            //indeterminate= circular progress
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dlg;
    }


    private void download(final Context context, String url, final ProgressBar progressBar, final ProgressDialog progressDialog){
//        progressDialog.show();                                //either progressDialog
        progressBar.setVisibility(View.VISIBLE);                //or progressBar
        Ion.with(context)
                .load(url)
// have a ProgressBar get updated automatically with the percent
                .progressBar(progressBar)
// and a ProgressDialog
//                .progressDialog(progressDialog)
// can also use a custom callback
                .progress(new ProgressCallback() {@Override
                public void onProgress(long downloaded, long total) {
                    Log.d("sayanp", "onProgress: "+downloaded+" / "+total);     //not available on UI thread
                }
                })
                //change the file name or file path here
                .write(new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"sample.pdf"))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
//                        progressDialog.dismiss();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Downloaded at "+file.getPath(), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
    }
}
