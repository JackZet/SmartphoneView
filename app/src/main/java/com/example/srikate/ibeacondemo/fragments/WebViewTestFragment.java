package com.example.srikate.ibeacondemo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.example.srikate.ibeacondemo.MainActivity;
import com.example.srikate.ibeacondemo.R;
import com.example.srikate.ibeacondemo.utils.JavaScriptInterface;

/**
 * Created by srikate on 10/4/2017 AD.
 * Source : https://github.com/inthepocket/ibeacon-scanner-android
 */

public class WebViewTestFragment extends Fragment{

    private WebView webview;
    private String url;
    public static WebViewTestFragment newInstance() {
        return new WebViewTestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            url = getArguments().getString("url");
        } catch (NullPointerException e){
            Log.d("Bundle", "Bundle is null");
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.web_view, container, false);
        webview = (WebView) v.findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        Log.d("newtag", getContext().toString());

        webview.addJavascriptInterface(new JavaScriptInterface(getActivity()), "Android");



        if (url != null){
            webview.loadUrl(url);
        } else {
            webview.loadUrl("https://bach2020.azurewebsites.net/Home/Navigation?id=2");
        }

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webview.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webview.canGoBack()) {

                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });

        return v;
    }


    private void webViewGoBack(){
        webview.goBack();
    }




}
