package com.example.mithilesh.hellodoc.mvp.screen_web_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mithilesh.hellodoc.R;
import com.example.mithilesh.hellodoc.mvp.BaseActivity;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;
import com.example.mithilesh.hellodoc.utils.AppConstants;

/**
 * Created by apple on 30/10/17.
 */

public class WebViewActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initMembers() {

        Intent intent = getIntent();
        Bundle dataBundle=  intent.getBundleExtra("bundle");

        showFragment(AppConstants.Screens.SCREEN_WEBVIEW, dataBundle);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }
}
