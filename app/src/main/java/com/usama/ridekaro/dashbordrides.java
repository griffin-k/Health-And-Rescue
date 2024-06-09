package com.usama.ridekaro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.froyo.usama.R;

import android.webkit.WebView;

public class dashbordrides extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbordrides);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("");
    }
}
