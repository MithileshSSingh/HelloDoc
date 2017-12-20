package com.example.mithilesh.hellodoc.mvp.screen_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mithilesh.hellodoc.R;
import com.example.mithilesh.hellodoc.di.RepositoryInjector;
import com.example.mithilesh.hellodoc.mvp.BaseFragment;
import com.example.mithilesh.hellodoc.mvp.model.Feeds;
import com.example.mithilesh.hellodoc.mvp.screen_web_view.WebViewActivity;
import com.example.mithilesh.hellodoc.mvp.screen_web_view.WebViewFragment;

import java.util.ArrayList;

/**
 * Created by apple on 30/10/17.
 */

public class MainFragment extends BaseFragment implements MainContract.View,MainContract.OnItemClickListener{

    public static final String TAG = MainFragment.class.getSimpleName();

    private MainContract.Presenter mPresenter;
    private ArrayList<Feeds> mListData = new ArrayList<>();

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.main, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private RecyclerView rvFeeds;

    private FeedsAdapter mAdapterCommitDetail;
    private RecyclerView.LayoutManager mLayoutManagerRV;
    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
        initRecycleView();
    }

    private void initRecycleView() {
            mAdapterCommitDetail = new FeedsAdapter(mActivity, mListData, this);
            mLayoutManagerRV = new LinearLayoutManager(
                    mActivity.getApplicationContext()
            );
            RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

            rvFeeds.setHasFixedSize(true);
            rvFeeds.setLayoutManager(mLayoutManagerRV);
            rvFeeds.setItemAnimator(itemAnimatorVertical);
            rvFeeds.setAdapter(mAdapterCommitDetail);

            mAdapterCommitDetail.notifyDataSetChanged();


    }

    @Override
    protected void initView() {
        rvFeeds = (RecyclerView) mView.findViewById(R.id.rvFeedList);

    }

    @Override
    protected void initMembers() {

        mPresenter = new MainPresenter(
                RepositoryInjector.provideRepository(getContext()), this
        );
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void executeQuery(String query) {
        mPresenter.search(query);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String title, String message) {

    }

    @Override
    public void searchSuccess(ArrayList<Feeds> feeds) {
        Log.v(TAG,feeds.toString());
        mListData = feeds;
        mAdapterCommitDetail.setListData(mListData);
        mAdapterCommitDetail.notifyDataSetChanged();
    }

    @Override
    public void searchFailed(int errorCode, String errorMessage) {

    }

    @Override
    public void onItemClick(int position) {

        Feeds feeds = mListData.get(position);

        Intent intent = new Intent(getActivity(),WebViewActivity.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("data",feeds);;

        intent.putExtra("bundle",dataBundle);

        startActivity(intent);

    }
}
