package com.example.mithilesh.hellodoc.mvp.screen_web_view;

import com.example.mithilesh.hellodoc.mvp.BasePresenter;
import com.example.mithilesh.hellodoc.mvp.BaseView;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

/**
 * Created by apple on 30/10/17.
 */

public class WebViewContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
    }
}
