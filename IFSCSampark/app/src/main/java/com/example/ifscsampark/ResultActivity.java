package com.example.ifscsampark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ResultActivity extends AppCompatActivity {

    private AdView mAdView;
    private AdView mAdView2;

    TextView bankNameBox;
    TextView bankCodeBox;
    TextView stateBox;
    TextView branchBox;
    TextView centreBox;
    TextView addressBox;
    TextView cityBox;
    TextView districtBox;
    TextView contactBox;
    TextView ifscCodeBox;
    TextView iso3166Box;
    TextView micrCodeBox;
    TextView impsBox;
    TextView neftBox;
    TextView rtgsBox;
    TextView upiBox;
    TextView swiftBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


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
        adView.setAdUnitId("ca-app-pub-1785962158595876/9798785853");

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


        // Banner Ads at the Bottom:
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        AdView adView2 = new AdView(this);
        adView2.setAdSize(AdSize.LARGE_BANNER);
        adView2.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        mAdView2.setAdListener(new AdListener() {
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

        bankNameBox = findViewById(R.id.bankNameBox);
        bankCodeBox = findViewById(R.id.bankCodeBox);
        stateBox = findViewById(R.id.stateBox);
        branchBox = findViewById(R.id.branchBox);
        centreBox = findViewById(R.id.centreBox);
        addressBox = findViewById(R.id.addressBox);
        cityBox = findViewById(R.id.cityBox);
        districtBox = findViewById(R.id.districtBox);
        contactBox = findViewById(R.id.contactBox);
        ifscCodeBox = findViewById(R.id.ifscCodeBox);
        iso3166Box = findViewById(R.id.iso3166Box);
        micrCodeBox = findViewById(R.id.micrCodeBox);
        impsBox = findViewById(R.id.impsBox);
        neftBox = findViewById(R.id.neftBox);
        rtgsBox = findViewById(R.id.rtgsBox);
        upiBox = findViewById(R.id.upiBox);
        swiftBox = findViewById(R.id.swiftBox);

        String passedBankName = getIntent().getExtras().getString("bankName");
        String passedbankCode = getIntent().getExtras().getString("bankCode");
        String passedstate = getIntent().getExtras().getString("state");
        String passedbranch = getIntent().getExtras().getString("branch");
        String passedcentre = getIntent().getExtras().getString("centre");
        String passedaddress = getIntent().getExtras().getString("address");
        String passedcity = getIntent().getExtras().getString("city");
        String passeddistrict = getIntent().getExtras().getString("district");
        String passedcontact = getIntent().getExtras().getString("contact");
        String passedifscCode = getIntent().getExtras().getString("ifscCode");
        String passediso3166 = getIntent().getExtras().getString("iso3166");
        String passedmicrcode = getIntent().getExtras().getString("micrcode");

        // Boolean values are received true or false or null
        String passedimps = getIntent().getExtras().getString("imps");
        String passedneft = getIntent().getExtras().getString("neft");
        String passedrtgs = getIntent().getExtras().getString("rtgs");
        String passedupi = getIntent().getExtras().getString("upi");
        String passedswift = getIntent().getExtras().getString("swift");

        bankNameBox.setText(passedBankName);
        bankCodeBox.setText(passedbankCode);
        stateBox.setText(passedstate);
        branchBox.setText(passedbranch);
        centreBox.setText(passedcentre);
        addressBox.setText(passedaddress);
        cityBox.setText(passedcity);
        districtBox.setText(passeddistrict);
        contactBox.setText(passedcontact);
        ifscCodeBox.setText(passedifscCode);
        iso3166Box.setText(passediso3166);
        micrCodeBox.setText(passedmicrcode);

        impsBox.setText(passedimps);
        neftBox.setText(passedneft);
        rtgsBox.setText(passedrtgs);
        upiBox.setText(passedupi);
        swiftBox.setText(passedswift);


        //Change Status Bar color:
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.PrimaryColor));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext() , MainActivity.class));
    }
}