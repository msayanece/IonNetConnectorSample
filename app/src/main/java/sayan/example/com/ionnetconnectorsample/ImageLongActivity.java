package sayan.example.com.ionnetconnectorsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import uk.co.senab.photoview.PhotoView;

public class ImageLongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String deepZoomImageURL = "https://static.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg";
        String imageURL = "https://www.w3schools.com/css/img_fjords.jpg";
        PhotoView photoView = new PhotoView(this);
        photoView.setMaximumScale(16f);
        setContentView(photoView);
        loadImageLongProcess(this,deepZoomImageURL,photoView);
    }

    private ProgressDialog createProgressDialog(){
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Loading...");
        dlg.setIndeterminate(false);
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dlg;
    }

    // This is the "long" way to do build an ImageView request... it allows you to set headers, etc.
    private void loadImageLongProcess(Context context, String imageURL, ImageView imageView) {
        final ProgressDialog dialog = createProgressDialog();
        dialog.show();
        Ion.with(context)
                .load(imageURL)
                .progressDialog(dialog)
                .withBitmap()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .deepZoom()
//                .animateLoad(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin_animation))
                .animateIn(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation))
                .intoImageView(imageView).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView result) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
    }
}
