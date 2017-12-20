package com.example.mithilesh.hellodoc.data.local;

import android.content.Context;

import com.example.mithilesh.hellodoc.data.DataSource;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;

/**
 * Created by mithilesh on 9/23/16.
 */
public class LocalDataSource implements DataSource {
    private static LocalDataSource ourInstance = new LocalDataSource();

    public static LocalDataSource getInstance(Context context) {
        return ourInstance;
    }

    private LocalDataSource() {
    }

    @Override
    public void search(
            String query,
            QueryCallBack callBack) {

    }
}
