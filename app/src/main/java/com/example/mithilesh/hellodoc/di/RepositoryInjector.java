package com.example.mithilesh.hellodoc.di;

import android.content.Context;

import com.example.mithilesh.hellodoc.data.Repository;
import com.example.mithilesh.hellodoc.data.local.LocalDataSource;
import com.example.mithilesh.hellodoc.data.remote.RemoteDataSource;


/**
 * Created by mithilesh on 9/4/16.
 */
public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(
                LocalDataSource.getInstance(context),
                RemoteDataSource.getInstance(context)
        );
    }
}
