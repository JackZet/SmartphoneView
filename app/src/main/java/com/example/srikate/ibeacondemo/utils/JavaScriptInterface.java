package com.example.srikate.ibeacondemo.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.Console;

public class JavaScriptInterface {
    Context mContext;

    public JavaScriptInterface(Context c){
            mContext = c;
    }

    @JavascriptInterface
    public int showToast(String text){
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        return 5;
    }
}
