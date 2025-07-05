package com.lisbongames.app.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lisbongames.R;
import com.lisbongames.shared.termux.TermuxConstants;

/** Basic embedded browser for viewing help pages. */
public final class MidinhoActivity extends AppCompatActivity {

    WebView mWebView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_midinho);
//        // WebView.setWebContentsDebuggingEnabled(true);
//        FrameLayout container = findViewById(R.id.webViewContainer);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//        // If WebView doesn't exist, create and configure it
//        if (WebViewHolder.midinhoWebView == null) {
//            WebView webView = new WebView(this);
//
//            WebSettings settings = webView.getSettings();
//            settings.setJavaScriptEnabled(true);
//            webView.setVerticalScrollBarEnabled(true);
//            webView.setHorizontalScrollBarEnabled(true);
//            webView.setFocusable(true);
//            webView.setFocusableInTouchMode(true);
//            webView.loadUrl(TermuxConstants.TERMUX_MIDINHO_URL);
//            WebViewHolder.midinhoWebView = webView;
//        }
//
//        ViewParent parent = WebViewHolder.midinhoWebView.getParent();
//        if (parent instanceof ViewGroup) {
//            ((ViewGroup) parent).removeView(WebViewHolder.midinhoWebView);
//        }
//
//        // Attach the WebView to this activity's layout
//        container.addView(WebViewHolder.midinhoWebView);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midinho);

        FrameLayout container = findViewById(R.id.webViewContainer);

        WebView webView = new WebView(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus(View.FOCUS_DOWN);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setOnTouchListener((v, event) -> {
            if (!v.hasFocus()) {
                v.requestFocus();
            }
            return false;
        });

        webView.loadUrl(TermuxConstants.TERMUX_MIDINHO_URL);
        container.addView(webView);

        // Save reference only if you must — and clear it in onDestroy
        WebViewHolder.midinhoWebView = webView;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
