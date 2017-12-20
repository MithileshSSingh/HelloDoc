package com.example.mithilesh.hellodoc.mvp.screen_main;

import com.example.mithilesh.hellodoc.data.DataSource;
import com.example.mithilesh.hellodoc.data.Repository;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

import java.util.ArrayList;

/**
 * Created by apple on 30/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Repository mRepository = null;
    private MainContract.View mView = null;

    private MainPresenter() {
    }

    public MainPresenter(Repository repository, MainContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }


    @Override
    public void search(
            String query) {

        mRepository.search(
                query,
                new DataSource.QueryCallBack() {
                    @Override
                    public void success(ArrayList<Feeds> feeds) {
                        mView.searchSuccess(feeds);
                    }

                    @Override
                    public void failed(int errorCode, String msgError) {
                        mView.searchFailed(errorCode, msgError);
                    }
                }
        );
    }
}
