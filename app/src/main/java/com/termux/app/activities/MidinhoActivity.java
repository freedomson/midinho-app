package com.termux.app.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.termux.R;
import com.termux.shared.activities.ReportActivity;
import com.termux.shared.file.FileUtils;
import com.termux.shared.models.ReportInfo;
import com.termux.app.models.UserAction;
import com.termux.shared.interact.ShareUtils;
import com.termux.shared.android.PackageUtils;
import com.termux.shared.termux.settings.preferences.TermuxAPIAppSharedPreferences;
import com.termux.shared.termux.settings.preferences.TermuxFloatAppSharedPreferences;
import com.termux.shared.termux.settings.preferences.TermuxTaskerAppSharedPreferences;
import com.termux.shared.termux.settings.preferences.TermuxWidgetAppSharedPreferences;
import com.termux.shared.android.AndroidUtils;
import com.termux.shared.termux.TermuxConstants;
import com.termux.shared.termux.TermuxUtils;
import com.termux.shared.activity.media.AppCompatActivityUtils;
import com.termux.shared.theme.NightMode;

/** Basic embedded browser for viewing help pages. */
public final class MidinhoActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatActivityUtils.setNightMode(this, NightMode.getAppNightMode().getName(), true);
        setContentView(R.layout.activity_midinho);

        FrameLayout container = findViewById(R.id.webViewContainer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // If WebView doesn't exist, create and configure it
        if (WebViewHolder.midinhoWebView == null) {
            WebView webView = new WebView(getApplicationContext());

            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.setVerticalScrollBarEnabled(true);
            webView.setHorizontalScrollBarEnabled(true);
            webView.loadUrl(TermuxConstants.TERMUX_MIDINHO_URL);
            WebViewHolder.midinhoWebView = webView;
        }

        ViewParent parent = WebViewHolder.midinhoWebView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(WebViewHolder.midinhoWebView);
        }

        // Attach the WebView to this activity's layout
        container.addView(WebViewHolder.midinhoWebView);
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
