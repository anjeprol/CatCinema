package com.pramont.catcines.catcinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String URI_LOGO_COYO = "http://cinemacoyoacan.com/wp-content/uploads/2017/05/cropped-favicon3-270x270.png";
    private final static String URI_LOGO_TONALA = "http://cinetonala.mx/wp-content/uploads/2016/05/logotonala.png";
    private final static String URI_LOGO_CINETECA = "http://www.cinetecanacional.net/favicon";
    private ImageView mIv_ico_coyo;
    private ImageView mIv_ico_tonala;
    private ImageView mIv_ico_cineteca;
    private ImageView mIv_map_coyo;
    private ImageView mIv_map_tonala;
    private ImageView mIv_map_cineteca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv_ico_coyo        = findViewById(R.id.iv_coyoacan);
        mIv_ico_cineteca    = findViewById(R.id.iv_cineteca);
        mIv_ico_tonala      = findViewById(R.id.iv_tonala);
        mIv_map_coyo        = findViewById(R.id.iv_map_coyo);
        mIv_map_cineteca    = findViewById(R.id.iv_map_cineteca);
        mIv_map_tonala      = findViewById(R.id.iv_map_tonala);
        getLogo(mIv_ico_coyo, URI_LOGO_COYO);
        getLogo(mIv_ico_cineteca, URI_LOGO_CINETECA);
        getLogo(mIv_ico_tonala, URI_LOGO_TONALA);

        mIv_ico_coyo.setOnClickListener(this);
        mIv_ico_cineteca.setOnClickListener(this);
        mIv_ico_tonala.setOnClickListener(this);
        mIv_map_coyo.setOnClickListener(this);
        mIv_map_cineteca.setOnClickListener(this);
        mIv_map_tonala.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_cineteca:

                break;
            case R.id.iv_coyoacan:

                break;
            case R.id.iv_tonala:

                break;
            case R.id .iv_map_cineteca:

                break;
            case R.id.iv_map_coyo:

                break;
            case R.id.iv_map_tonala:

                break;
        }
    }

    /**
     * Gets the icons from the proper cinema
     *
     * @param imageView the ImageView.
     * @param uri the URI where to get the logo.
     */
    private void getLogo(ImageView imageView, String uri) {
        Glide.with(this)
                .load(uri)
                .placeholder(R.mipmap.ic_splash_foreground)
                .error(R.mipmap.ic_not_found)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        // log exception
                        Log.e("Glide", "Error loading image", e);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}
