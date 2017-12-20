package com.example.mithilesh.hellodoc.mvp.screen_web_view;

import com.example.mithilesh.hellodoc.data.DataSource;
import com.example.mithilesh.hellodoc.data.Repository;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

/**
 * Created by apple on 30/10/17.
 */

public class WebViewPresenter implements WebViewContract.Presenter {

    private Repository mRepository = null;
    private WebViewContract.View mView = null;

    private WebViewPresenter() {
    }

    public WebViewPresenter(Repository repository, WebViewContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

}
