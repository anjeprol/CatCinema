package com.pramont.catcines.catcinema;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PHONE_CALL = 1;
    private final static String URI_LOGO_COYO = "http://cinemacoyoacan.com/" +
            "wp-content/uploads/2017/05/cropped-favicon3-270x270.png";
    private final static String URI_LOGO_TONALA = "http://cinetonala.mx/" +
            "wp-content/uploads/2016/05/logotonala.png";
    private final static String URI_LOGO_CINETECA = "http://www.cinetecanacional.net/favicon";
    private final static String URI_LOGO_AUTO = "http://www.autocinemacoyote.com/image/2015/assets/autocinema-coyote-logo-2015.png";
    private final static String COYO = "coyo";
    private final static String TONA = "tonala";
    private final static String CINE = "cineteca";
    private final static String AUTO_COY_INSUR = "coyoinsur";
    private final static String AUTO_COY_POL = "coyopol";
    private Intent mIntent;
    private HashMap<String, String> mCatCinemas;
    private ImageView mIv_ico_coyo;
    private ImageView mIv_ico_tonala;
    private ImageView mIv_ico_cineteca;
    private ImageView mIv_ico_coyo_insur;
    private ImageView mIv_ico_coyo_pol;
    private ImageView mIv_map_coyo;
    private ImageView mIv_map_tonala;
    private ImageView mIv_map_cineteca;
    private ImageView mIv_map_coyo_insur;
    private ImageView mIv_map_coyo_pol;
    private ImageView mIv_call_coyo;
    private ImageView mIv_call_cineteca;
    private ImageView mIv_call_tonala;
    private ImageView mIv_call_coyo_insur;
    private ImageView mIv_call_coyo_pol;
    private LinearLayout mLinearLay_coyo;
    private LinearLayout mLinearLay_cinetaca;
    private LinearLayout mLinearLay_tonala;
    private LinearLayout mLinearLay_coyo_insur;
    private LinearLayout mLinearLay_coyo_pol;
    private AdView mAdViewTop;
    private AdView mAdViewMiddle;
    private AdView mAdViewBottom;

    @Override
    public void onPause() {
        if (mAdViewTop != null) {
            mAdViewTop.pause();
        }
        if (mAdViewMiddle != null) {
            mAdViewMiddle.pause();
        }
        if (mAdViewBottom != null) {
            mAdViewBottom.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mAdViewTop != null) {
            mAdViewTop.resume();
        }
        if (mAdViewMiddle != null) {
            mAdViewMiddle.resume();
        }
        if (mAdViewBottom != null) {
            mAdViewBottom.resume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mAdViewTop != null) {
            mAdViewTop.destroy();
        }
        if (mAdViewMiddle != null) {
            mAdViewMiddle.destroy();
        }
        if (mAdViewBottom != null) {
            mAdViewBottom.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        mAdViewTop = findViewById(R.id.adViewTop);
        mAdViewMiddle = findViewById(R.id.adViewMiddle);
        mAdViewBottom = findViewById(R.id.adViewBottom);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build();

        mAdViewTop.loadAd(adRequest);
        mAdViewMiddle.loadAd(adRequest);
        mAdViewBottom.loadAd(adRequest);

        mCatCinemas = new HashMap<>();
        mCatCinemas.put(COYO, "http://cinemacoyoacan.com/");
        mCatCinemas.put(TONA, "http://cinetonala.mx/");
        mCatCinemas.put(CINE, "http://www.cinetecanacional.net/movil.php");
        mCatCinemas.put(AUTO_COY_INSUR, "https://cartelera.autocinemacoyote.com/?cine=4");
        mCatCinemas.put(AUTO_COY_POL, "https://cartelera.autocinemacoyote.com/?cine=5");

        //requestPermissions();
        overlayDemo(getString(R.string.cartelera), getString(R.string.msg_cartelera_overlay),
                new ViewTarget(R.id.iv_cineteca, this));
        mIv_ico_coyo = findViewById(R.id.iv_coyoacan);
        mIv_ico_cineteca = findViewById(R.id.iv_cineteca);
        mIv_ico_tonala = findViewById(R.id.iv_tonala);
        mIv_ico_coyo_insur = findViewById(R.id.iv_coyote_insur);
        mIv_ico_coyo_pol = findViewById(R.id.iv_coyote_pol);

        mIv_map_coyo = findViewById(R.id.iv_map_coyo);
        mIv_map_cineteca = findViewById(R.id.iv_map_cineteca);
        mIv_map_tonala = findViewById(R.id.iv_map_tonala);
        mIv_map_coyo_insur = findViewById(R.id.iv_map_coyote_insur);
        mIv_map_coyo_pol = findViewById(R.id.iv_map_coyote_pol);

        mIv_call_cineteca = findViewById(R.id.iv_call_cineteca);
        mIv_call_coyo = findViewById(R.id.iv_call_coyo);
        mIv_call_tonala = findViewById(R.id.iv_call_tonala);
        mIv_call_coyo_insur = findViewById(R.id.iv_call_coyote_insur);
        mIv_call_coyo_pol = findViewById(R.id.iv_call_coyote_pol);

        mLinearLay_tonala = findViewById(R.id.ll_tonala);
        mLinearLay_coyo = findViewById(R.id.ll_coyo);
        mLinearLay_cinetaca = findViewById(R.id.ll_cineteca);
        mLinearLay_coyo_insur = findViewById(R.id.ll_coyote_insur);
        mLinearLay_coyo_pol = findViewById(R.id.ll_coyote_pol);

        getLogo(mIv_ico_coyo, URI_LOGO_COYO);
        getLogo(mIv_ico_cineteca, URI_LOGO_CINETECA);
        getLogo(mIv_ico_tonala, URI_LOGO_TONALA);
        getLogo(mIv_ico_coyo_insur, URI_LOGO_AUTO);
        getLogo(mIv_ico_coyo_pol, URI_LOGO_AUTO);

        mIv_ico_coyo.setOnClickListener(this);
        mIv_ico_cineteca.setOnClickListener(this);
        mIv_ico_tonala.setOnClickListener(this);
        mIv_ico_coyo_insur.setOnClickListener(this);
        mIv_ico_coyo_pol.setOnClickListener(this);

        mIv_map_coyo.setOnClickListener(this);
        mIv_map_cineteca.setOnClickListener(this);
        mIv_map_tonala.setOnClickListener(this);
        mIv_map_coyo_insur.setOnClickListener(this);
        mIv_map_coyo_pol.setOnClickListener(this);

        mIv_call_tonala.setOnClickListener(this);
        mIv_call_cineteca.setOnClickListener(this);
        mIv_call_coyo.setOnClickListener(this);
        mIv_call_coyo_insur.setOnClickListener(this);
        mIv_call_coyo_pol.setOnClickListener(this);

        mLinearLay_cinetaca.setOnClickListener(this);
        mLinearLay_coyo.setOnClickListener(this);
        mLinearLay_tonala.setOnClickListener(this);
        mLinearLay_coyo_insur.setOnClickListener(this);
        mLinearLay_coyo_pol.setOnClickListener(this);

    }

    /**
     * To show the overlay demo just once, in order to show how it's works the app interaction.
     *
     * @param titleLabel String title to show at overlay.
     * @param msg        String message to set at overlay.
     * @param target     TargetView to set at overlay.
     */
    private void overlayDemo(String titleLabel, String msg, ViewTarget target) {
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getResources().getDimension(R.dimen.text_size_message_overlay));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.rgb(241, 140, 18));

        TextPaint title = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        title.setTextSize(getResources().getDimension(R.dimen.text_size_title_overlay));
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setColor(Color.rgb(13, 35, 94));

        ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                .withNewStyleShowcase()
                .setTarget(target)
                .setContentTextPaint(paint)
                .setContentTitle(titleLabel)
                .setContentText(msg)
                .setContentTitlePaint(title)
                .hideOnTouchOutside()
                .singleShot(45)
                .build();

        showcaseView.setDetailTextAlignment(Layout.Alignment.ALIGN_CENTER);
        showcaseView.setTitleTextAlignment(Layout.Alignment.ALIGN_CENTER);
        showcaseView.forceTextPosition(ShowcaseView.ABOVE_SHOWCASE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_cineteca:
                showActivity(mCatCinemas.get(CINE));
                break;

            case R.id.iv_coyoacan:
                showActivity(mCatCinemas.get(COYO));
                break;

            case R.id.iv_tonala:
                showActivity(mCatCinemas.get(TONA));
                break;

            case R.id.iv_coyote_insur:
                showActivity(mCatCinemas.get(AUTO_COY_INSUR));
                break;

            case R.id.iv_coyote_pol:
                showActivity(mCatCinemas.get(AUTO_COY_POL));
                break;

            case R.id.ll_cineteca:
                showActivity(mCatCinemas.get(CINE));
                break;

            case R.id.ll_coyo:
                showActivity(mCatCinemas.get(COYO));
                break;

            case R.id.ll_tonala:
                showActivity(mCatCinemas.get(TONA));
                break;

            case R.id.ll_coyote_insur:
                showActivity(mCatCinemas.get(AUTO_COY_INSUR));
                break;

            case R.id.ll_coyote_pol:
                showActivity(mCatCinemas.get(AUTO_COY_POL));
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

            case R.id.iv_map_coyote_insur:
                getMaps(getString(R.string.map_coyo_insur));
                break;

            case R.id.iv_map_coyote_pol:
                getMaps(getString(R.string.map_coyo_pol));
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
            case R.id.iv_call_coyote_insur:
                makeCall(getString(R.string.phone_coyo_insr));
                break;
            case R.id.iv_call_coyote_pol:
                makeCall(getString(R.string.phone_coyo_pol));
                break;
        }
    }

    /**
     * This method is to set intent with the proper URL cinema and launch the activity.
     *
     * @param url The string with the URL.
     */
    private void showActivity(String url) {
        Intent intent = new Intent(this, ShowTimeActivity.class);
        final String TAG = "URL";
        intent.putExtra(TAG, url);
        startActivity(intent);
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
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        Log.e("Glide", "Error loading image", e);
                        // important to return false so the error placeholder can be placed
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
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
    @SuppressLint("MissingPermission")
    private void makeCall(String phone) {
        final String TAG = "Permissions";
        mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        try
        {
            startActivity(mIntent);
        }
        catch (Exception ex)
        {
            requestPermissions();
            Log.d(TAG, "No permissions granted \n" + ex.getMessage());
        }
    }

    /**
     * Gets the location and send the directions using google maps.
     *
     * @param uri String uri from maps.
     */
    private void getMaps(String uri) {
        mIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri));
        startActivity(mIntent);

    }

    /**
     * To request all the permission once the application starts.
     */
    private void requestPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            /**
             * Asking for call permission.
             */
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            }
        }
    }
}
