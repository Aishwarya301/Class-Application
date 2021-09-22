package com.aishwarya.aclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class home extends AppCompatActivity {
    WebView mywebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mywebview = (WebView) findViewById(R.id.webView);
        mywebview.loadUrl("https://aissmscoe.akronsystems.com/pLogin.aspx");
    }
}
