package com.pramont.catcines.catcinema;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
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
    private static final int REQUEST_PHONE_CALL = 1;
    private final static String URI_LOGO_COYO = "http://cinemacoyoacan.com/wp-content/uploads/2017/05/cropped-favicon3-270x270.png";
    private final static String URI_LOGO_TONALA = "http://cinetonala.mx/wp-content/uploads/2016/05/logotonala.png";
    private final static String URI_LOGO_CINETECA = "http://www.cinetecanacional.net/favicon";
    private ImageView mIv_ico_coyo;
    private ImageView mIv_ico_tonala;
    private ImageView mIv_ico_cineteca;
    private ImageView mIv_map_coyo;
    private ImageView mIv_map_tonala;
    private ImageView mIv_map_cineteca;
    private ImageView mIv_call_coyo;
    private ImageView mIv_call_cineteca;
    private ImageView mIv_call_tonala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv_ico_coyo = findViewById(R.id.iv_coyoacan);
        mIv_ico_cineteca = findViewById(R.id.iv_cineteca);
        mIv_ico_tonala = findViewById(R.id.iv_tonala);
        mIv_map_coyo = findViewById(R.id.iv_map_coyo);
        mIv_map_cineteca = findViewById(R.id.iv_map_cineteca);
        mIv_map_tonala = findViewById(R.id.iv_map_tonala);
        mIv_call_cineteca = findViewById(R.id.iv_call_cineteca);
        mIv_call_coyo = findViewById(R.id.iv_call_coyo);
        mIv_call_tonala = findViewById(R.id.iv_call_tonala);


        getLogo(mIv_ico_coyo, URI_LOGO_COYO);
        getLogo(mIv_ico_cineteca, URI_LOGO_CINETECA);
        getLogo(mIv_ico_tonala, URI_LOGO_TONALA);

        mIv_ico_coyo.setOnClickListener(this);
        mIv_ico_cineteca.setOnClickListener(this);
        mIv_ico_tonala.setOnClickListener(this);
        mIv_map_coyo.setOnClickListener(this);
        mIv_map_cineteca.setOnClickListener(this);
        mIv_map_tonala.setOnClickListener(this);
        mIv_call_tonala.setOnClickListener(this);
        mIv_call_cineteca.setOnClickListener(this);
        mIv_call_coyo.setOnClickListener(this);

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

            case R.id.iv_map_cineteca:
                getMaps(getString(R.string.map_cineteca));
                break;

            case R.id.iv_map_coyo:
                getMaps(getString(R.string.map_coyo));
                break;

            case R.id.iv_map_tonala:
                getMaps(getString(R.string.map_tonala));
                break;

            case R.id.iv_call_cineteca:
                makeCall(getString(R.string.phone_cineteca));
                break;

            case R.id.iv_call_coyo:
                makeCall(getString(R.string.phone_coyo));
                break;

            case R.id.iv_call_tonala:
                makeCall(getString(R.string.phone_tonala));
                break;
        }
    }

    /**
     * Gets the icons from the proper cinema
     *
     * @param imageView the ImageView.
     * @param uri       the URI where to get the logo.
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

    /**
     * To make a call to proper cinema.
     *
     * @param phone String phone number.
     */
    private void makeCall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
        else
        {
            startActivity(intent);
        }
    }

    /**
     * Gets the location and send the directions using google maps.
     * @param uri String uri from maps.
     */
    private void getMaps(String uri) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri));
        startActivity(intent);

    }
}
