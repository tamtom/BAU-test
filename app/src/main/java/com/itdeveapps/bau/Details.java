package com.itdeveapps.bau;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Details extends AppCompatActivity {
    public static final String EXTRA_URL = "extra.url";
    private ProgressDialog progressDoalog;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_details);
        progressDoalog = new ProgressDialog(this);
        progressDoalog.show();
        progressDoalog.setCancelable(false);
        webView = (WebView) findViewById(R.id.webView);
        progressDoalog.setMessage("loading ... ");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(bundle.getString(EXTRA_URL));
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                progressDoalog.hide();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        if(progressDoalog!=null){
            progressDoalog.dismiss();
        progressDoalog = null;}
        super.onDestroy();
    }
}
