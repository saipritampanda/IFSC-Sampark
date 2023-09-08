package com.example.ifscsampark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class Donate30Activity extends AppCompatActivity {

    private AdView mAdView;
    Button pay30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate30);

        // Ads Initialization
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Banner Ads at the Top:
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-1785962158595876/7180608790");

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        pay30 = findViewById(R.id.pay30);

        pay30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                decodeAndSearchQRCode();

            }
        });



        //Change Status Bar color:
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.PrimaryColor));
    }

    private void decodeAndSearchQRCode() {
        // Load the QR code image from drawable resource
        Bitmap qrCodeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.donate_rs30_qrcode_image);

        // Convert the Bitmap to a binary bitmap for QR code scanning
        RGBLuminanceSource source = new RGBLuminanceSource(qrCodeBitmap.getWidth(), qrCodeBitmap.getHeight(), getRGBIntArray(qrCodeBitmap));
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        // Initialize the QR code reader
        Reader reader = new MultiFormatReader(); // Reader (interface) is imported from com.zxing instead of java.io

        try {
            // Decode the QR code
            Result result = reader.decode(binaryBitmap);
            String extractedText = result.getText();

            // Search the extracted text on Google
            openGoogleSearch(extractedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openGoogleSearch(String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(query));
        startActivity(intent);
    }

    private int[] getRGBIntArray(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        return pixels;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , DonationActivity.class));
    }
}