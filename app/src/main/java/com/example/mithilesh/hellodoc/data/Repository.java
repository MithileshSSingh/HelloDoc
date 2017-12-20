package com.example.mithilesh.hellodoc.data;


import com.example.mithilesh.hellodoc.mvp.model.Feeds;

import java.util.ArrayList;

/**
 * Created by mithilesh on 8/19/16.
 */
public class Repository implements DataSource {


    private static Repository INSTANCE = null;

    private DataSource mLocalDataSource = null;
    private DataSource mRemoteDataSource = null;

    private Repository() {

    }

    private Repository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(DataSource localDataSource, DataSource remoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new Repository(localDataSource, remoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void search(
            final String query,
            final QueryCallBack callBack) {

        mRemoteDataSource.search(
                query,
                new QueryCallBack() {
                    @Override
                    public void success(ArrayList<Feeds> feeds) {
                        callBack.success(feeds);
                    }

                    @Override
                    public void failed(int errorCode, String msgError) {
                        callBack.failed(errorCode,msgError);
                    }
                }
        );

    }
}