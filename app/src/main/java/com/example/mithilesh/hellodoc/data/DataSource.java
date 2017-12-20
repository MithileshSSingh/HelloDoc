package com.example.mithilesh.hellodoc.data;

import com.example.mithilesh.hellodoc.mvp.model.Feeds;

import java.util.ArrayList;

/**
 * Created by mithilesh on 8/17/16.
 */
public interface DataSource {

    interface QueryCallBack {
        void success(ArrayList<Feeds> feeds);

        void failed(int errorCode, String msgError);
    }

    void search(
            String query,
            QueryCallBack callBack);
}
