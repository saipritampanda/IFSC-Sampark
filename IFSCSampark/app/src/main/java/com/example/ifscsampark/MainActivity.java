package com.example.ifscsampark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private AdView mAdView2;

    EditText ifscEditTextView;
    Button bankDetailsBtn;
    Button donationBtn;

    // creating a variable for request queue.
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        // Banner Ads at the Bottom:
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        AdView adView2 = new AdView(this);
        adView2.setAdSize(AdSize.LARGE_BANNER);
        adView2.setAdUnitId("ca-app-pub-1785962158595876/9798785853");

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


        ifscEditTextView = findViewById(R.id.ifscEditTextView);
        bankDetailsBtn = findViewById(R.id.bankDetailsBtn);
        donationBtn = findViewById(R.id.donationBtn);

        // initializing our request queue variable with request queue and passing our context to it.
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        bankDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ifsc = ifscEditTextView.getText().toString();

                if(TextUtils.isEmpty(ifsc) || ifsc.length() != 11){
                    Toast.makeText(MainActivity.this, "Please enter valid IFSC code", Toast.LENGTH_SHORT).show();
                }
                else{
                    getDataFromIFSCCode(ifsc);
                }
            }
        });

        donationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , DonationActivity.class);
                startActivity(intent);
            }
        });

        //Change Status Bar color:
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.PrimaryColor));
    }

    private void getDataFromIFSCCode(String ifsc) {
        // Clearing our cache of request queue
        mRequestQueue.getCache().clear();

        // Get instance of Vibrator from current Context
        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Below is the url from where we will be getting our response in the json format.
        String url = "https://ifsc.razorpay.com/" + ifsc; // Razorpay github

        // Below line is use to initialize our request queue.
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // This method is used to get the response from the API.
            try{
                if(response.getString("IFSC").length() != 11){
                    // Checking if the response is not loaded and status for the repose is fail. if response status is failure we are displaying an invalid IFSC code in our text view.
                    Toast.makeText(MainActivity.this, "Please enter a valid IFSC code", Toast.LENGTH_SHORT).show();
                }
                else{
                    // If the status is successful we are extracting data from JSON file

                    // Use the response directly (no need to call getJSONObject)
                    JSONObject dataObj = response;

                    String bankName = dataObj.optString("BANK");
                    String bankCode = dataObj.optString("BANKCODE");
                    String state = dataObj.optString("STATE");
                    String branch = dataObj.optString("BRANCH");
                    String centre = dataObj.optString("CENTRE");
                    String address = dataObj.optString("ADDRESS");
                    String city = dataObj.optString("CITY");
                    String district = dataObj.optString("DISTRICT");
                    String contact = dataObj.optString("CONTACT");
                    String ifscCode = dataObj.optString("IFSC");
                    String iso3166 = dataObj.optString("ISO3166");
                    String micrcode = dataObj.optString("MICRCODE");

                    String imps = dataObj.optString("IMPS");
                    String neft = dataObj.optString("NEFT");
                    String rtgs = dataObj.optString("RTGS");
                    String upi = dataObj.optString("UPI");
                    String swift = dataObj.optString("SWIFT");

                    // After extracting this data we are displaying that data in our text view, in another Activity.
                    // We will be sending our data from one activity to another using Intent (Extra).

                    Intent intent = new Intent(MainActivity.this , ResultActivity.class);
                    intent.putExtra("bankName" , bankName);
                    intent.putExtra("bankCode" , bankCode);
                    intent.putExtra("state" , state);
                    intent.putExtra("branch" , branch);
                    intent.putExtra("centre" , centre);
                    intent.putExtra("address" , address);
                    intent.putExtra("city" , city);
                    intent.putExtra("district" , district);
                    intent.putExtra("contact" , contact);
                    intent.putExtra("ifscCode" , ifscCode);
                    intent.putExtra("iso3166" , iso3166);
                    intent.putExtra("micrcode" , micrcode);
                    intent.putExtra("imps" , imps);
                    intent.putExtra("neft" , neft);
                    intent.putExtra("rtgs" , rtgs);
                    intent.putExtra("upi" , upi);
                    intent.putExtra("swift" , swift);
                    startActivity(intent);

                    // Vibrate for 250 milliseconds
                    vibrate.vibrate(250);
                }
            }
            catch (JSONException e) {
                // If we get any error while loading data we are setting our text as invalid IFSC code.
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Please enter a valid IFSC code - status failed", Toast.LENGTH_SHORT).show();

            }
        }, error -> {
            // If we get any error while loading json data we are setting our text to invalid IFSC code.
            Toast.makeText(MainActivity.this, "Please enter a valid IFSC code - status failed", Toast.LENGTH_SHORT).show();
        });

        // Below line is use for adding object request to our request queue.
        requestQueue.add(objectRequest);

    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            MainActivity.this.finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_LONG).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1600);
    }

}
