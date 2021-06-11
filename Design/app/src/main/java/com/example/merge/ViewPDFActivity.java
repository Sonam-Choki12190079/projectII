package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;

public class ViewPDFActivity extends AppCompatActivity {

    WebView TeacherPDFView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdfactivity);

        TeacherPDFView = (WebView) findViewById(R.id.webView);
        TeacherPDFView.getSettings().setJavaScriptEnabled(true);

        String TeacherPDFName = getIntent().getStringExtra("PDF");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Opening....");

        TeacherPDFView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });
        String url = "";
        try{
            url = URLEncoder.encode(TeacherPDFName,"UTF-8");
        }
        catch(Exception e){ }

        TeacherPDFView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
    }
}