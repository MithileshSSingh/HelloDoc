package com.example.mithilesh.hellodoc.mvp.screen_web_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mithilesh.hellodoc.R;
import com.example.mithilesh.hellodoc.di.RepositoryInjector;
import com.example.mithilesh.hellodoc.mvp.BaseFragment;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

/**
 * Created by apple on 30/10/17.
 */

public class WebViewFragment extends BaseFragment implements WebViewContract.View {

    public static final String TAG = WebViewFragment.class.getSimpleName();

    private WebViewContract.Presenter mPresenter;
    private Feeds data;

    public WebViewFragment() {
    }

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.webview, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
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
        mPresenter = new WebViewPresenter(
                RepositoryInjector.provideRepository(getContext()), this
        );

        Bundle bundle = getArguments().getBundle("bundle");

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void executeQuery(String query) {

    }

    @Override
    public void setPresenter(WebViewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String title, String message) {

    }

}
