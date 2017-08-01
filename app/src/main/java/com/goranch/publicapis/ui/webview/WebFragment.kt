package com.goranch.publicapis.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.goranch.publicapis.R
import com.goranch.publicapis.ui.food.fragment.DetailsFragment

class WebFragment : Fragment() {

    @JvmField
    @BindView(R.id.web_view)
    internal var vw: WebView? = null

    @JvmField
    @BindView(R.id.progressBar)
    internal var progressBar: ProgressBar? = null

    internal var contentUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            if (arguments.containsKey(DetailsFragment.URL)) {
                contentUrl = arguments.getString(DetailsFragment.URL)
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.web_view_fragment, container, false)

        ButterKnife.bind(this, v)

        vw!!.settings.javaScriptEnabled = true

        vw!!.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressBar!!.visibility = View.GONE
            }
        }

        vw!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {

                progressBar!!.progress = progress
                if (progress == 100)
                //Make the bar disappear after URL is loaded
                    progressBar!!.visibility = View.GONE
            }
        }

        if (contentUrl != null) {
            vw!!.loadUrl(contentUrl)
        }

        return v
    }

    companion object {

        fun newInstance(url: String): WebFragment {
            val b = Bundle()
            b.putString(DetailsFragment.URL, url)

            val fragment = WebFragment()
            fragment.arguments = b
            return fragment
        }
    }
}
