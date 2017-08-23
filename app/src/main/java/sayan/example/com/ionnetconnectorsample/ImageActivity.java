package sayan.example.com.ionnetconnectorsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import uk.co.senab.photoview.PhotoView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String imageURL = "https://www.w3schools.com/css/img_fjords.jpg";
        PhotoView photoView = new PhotoView(this);
        photoView.setMaximumScale(16f);
        setContentView(photoView);
        loadImageDirectly(this, imageURL, photoView);
    }

    // directly load image in the ImageView
    private void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
        Ion.with(imageView)
                .placeholder(R.drawable.placeholder)                //image when loading
                .error(R.drawable.error)                            //image if error/ no connection/ wrong url etc
                //uncomment this line and set imageURL with wrong URL and see error image animation
//                .animateLoad(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin_animation))
                //this defines animation of the successfully loaded image (animate it in)
                .animateIn(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation))
                .load(imageURL);        //the url for image
    }
}
