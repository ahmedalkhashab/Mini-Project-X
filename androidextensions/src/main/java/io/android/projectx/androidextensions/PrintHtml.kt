package io.android.projectx.androidextensions

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

private var mWebView: WebView? = null

fun Context.doWebViewPrint(htmlDocument: String, appName: String) {
    // Create a WebView object specifically for printing
    val webView = WebView(this)
    webView.webViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false

        override fun onPageFinished(view: WebView, url: String) {
            createWebPrintJob(this@doWebViewPrint, view, appName)
            mWebView = null
        }
    }
    // Generate an HTML document on the fly:
    webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null)
    // Keep a reference to WebView object until you pass the PrintDocumentAdapter
    // to the PrintManager
    mWebView = webView
}

private fun createWebPrintJob(context: Context, webView: WebView, appName: String) {
    // Get a PrintManager instance
    (context.getSystemService(Context.PRINT_SERVICE) as? PrintManager)?.let { printManager ->
        val jobName = "$appName Document"
        // Get a print adapter instance
        val printAdapter = webView.createPrintDocumentAdapter(jobName)
        // Create a print job with name and adapter instance
        printManager.print(
            jobName,
            printAdapter,
            PrintAttributes.Builder().build()
        )/*.also { //printJob ->
            // Save the job object for later status checking
            //printJobs += printJob
        }*/
    }
}