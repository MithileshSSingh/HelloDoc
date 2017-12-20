package com.example.mithilesh.hellodoc.mvp.screen_main;

import com.example.mithilesh.hellodoc.mvp.BasePresenter;
import com.example.mithilesh.hellodoc.mvp.BaseView;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

import java.util.ArrayList;

/**
 * Created by apple on 30/10/17.
 */

public class MainContract {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    interface View extends BaseView<Presenter> {

        void searchSuccess(ArrayList<Feeds> feeds);

        void searchFailed(int errorCode, String errorMessage);

    }

    interface Presenter extends BasePresenter {
        void search(
                String query);

    }
}
