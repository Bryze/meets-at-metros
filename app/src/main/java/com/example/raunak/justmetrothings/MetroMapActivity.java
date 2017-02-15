package com.example.raunak.justmetrothings;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MetroMapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_map);

        WebView metro_map_webview = (WebView)findViewById(R.id.metro_map_WebView);
        metro_map_webview.loadUrl("file:///android_asset/metro_map_webview.html");
        metro_map_webview.getSettings().setBuiltInZoomControls(true);
        metro_map_webview.getSettings().setDisplayZoomControls(false);
        metro_map_webview.setVerticalScrollBarEnabled(false);
        metro_map_webview.setHorizontalScrollBarEnabled(false);
    }
}
