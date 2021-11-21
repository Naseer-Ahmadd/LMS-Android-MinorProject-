package com.ghskhawajabagh.lms.TeacherSection.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ghskhawajabagh.lms.R;

public class TeacherDashboard extends Fragment {

    View root;
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_teacher_dashboard, container, false);
        webView=root.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ghs-sms.web.app/about");

        // this will enable the javascipt.
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.

        return  root;
    }

   void showLoader(){

    }


    void hideLoader()
    {

    }



}