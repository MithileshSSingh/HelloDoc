package com.example.mithilesh.hellodoc.mvp;

/**
 * Created by mithilesh on 8/18/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading(String title, String message);
}
