package com.example.mithilesh.hellodoc.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mithilesh on 14/12/17.
 */

public class ResponseSearch {
    @Expose
    @SerializedName("hits")
    private ArrayList<Feeds> feeds;

    public ArrayList<Feeds> getFeeds() {
        return feeds;
    }

    public void setFeeds(ArrayList<Feeds> feeds) {
        this.feeds = feeds;
    }

    @Override
    public String toString() {
        return "ResponseSearch{" +
                "feeds=" + feeds +
                '}';
    }
}
