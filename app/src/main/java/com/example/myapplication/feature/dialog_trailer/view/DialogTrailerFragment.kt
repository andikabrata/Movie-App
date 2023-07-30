package com.example.myapplication.feature.dialog_trailer.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R

/**
 * @author Andika Bratadirja
 * @date 30/07/2023
 */
class DialogTrailerFragment(var keyVideo: String) : DialogFragment() {
    private lateinit var close: ImageView
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close = view.findViewById(R.id.ivClose)
        webView = view.findViewById(R.id.webview)
        initializeWebView()
        loadIframeContent(keyVideo)
        close.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // Handle URL loading, if necessary
                return false
            }
        }
    }

    private fun loadIframeContent(keyVideo: String) {
        // Example: Loading a YouTube video
        val iframeUrl = "https://www.youtube.com/embed/$keyVideo"
        val iframeContent = """
                                <!DOCTYPE html>
                                <html>
                                <head>
                                    <title>Embedded Content</title>
                                </head>
                                <body>
                                    <div style="width: 100%; height: 100%; overflow: hidden; position: relative;">
                                        <iframe
                                            width="100%"
                                            height="250"
                                            position: absolute
                                            src='$iframeUrl'
                                            frameborder="0"
                                            allowfullscreen>
                                        </iframe>
                                    </div>
                                </body>
                                </html>
                            """.trimIndent()
        webView.loadData(iframeContent, "text/html", "utf-8")
    }

    companion object {
        fun show(fragmentManager: FragmentManager?, keyVideo: String): DialogTrailerFragment {
            val catDialog = DialogTrailerFragment(keyVideo = keyVideo)
            fragmentManager?.let { catDialog.show(it, "Coming Soon Dialog") }
            return catDialog
        }
    }
}