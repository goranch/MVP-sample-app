package com.goranch.shazammvp.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.goranch.shazammvp.R;
import com.goranch.shazammvp.ui.details.DetailsShazamFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebShazamFragment extends Fragment {

    @Bind(R.id.web_view)
    WebView vw;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    String contentUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(DetailsShazamFragment.URL)) {
            contentUrl = getArguments().getString(DetailsShazamFragment.URL);
        }
    }



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.web_view_fragment, container, false);

        ButterKnife.bind(this, v);

        vw.getSettings().setJavaScriptEnabled(true);

        vw.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        vw.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                progressBar.setProgress(progress);
                if (progress == 100)
                    //Make the bar disappear after URL is loaded
                    progressBar.setVisibility(View.GONE);
            }
        });

        if (contentUrl != null) {
            vw.loadUrl(contentUrl);
        }

        return v;
    }
}
