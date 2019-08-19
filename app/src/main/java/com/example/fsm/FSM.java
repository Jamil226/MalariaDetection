package com.example.fsm;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FSM extends AppCompatActivity {
    private final String url = "http://e-till2.co.uk/poundwise/pdanew";
//    private final String url-old = "https://www.e-till2.co.uk/maemes_portsmouth/fsm/";
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsm);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        //webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setAllowContentAccess(true);
//        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl(url);

    }
//    @Override
//    public void onBackPressed() {
////  super.onBackPressed();
////  Not calling **super**, disables back button in current screen.
//    }

//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        ActivityManager activityManager = (ActivityManager) getApplicationContext()
//                .getSystemService(Context.ACTIVITY_SERVICE);
//
//        activityManager.moveTaskToFront(getTaskId(), 0);
//    }



}
