package com.example.mithilesh.hellodoc.mvp;

/**
 * Created by mithilesh on 9/4/16.
 */
public interface BaseViewHolder<T> {
    void apply(T data, int position);
}
