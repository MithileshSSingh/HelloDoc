package com.example.mithilesh.hellodoc.mvp.screen_main;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.mithilesh.hellodoc.R;
        import com.example.mithilesh.hellodoc.mvp.BaseViewHolder;
        import com.example.mithilesh.hellodoc.mvp.model.Feeds;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

/**
 * Created by mithilesh on 9/4/16.
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Feeds> mListData = Collections.emptyList();

    private MainContract.OnItemClickListener mListener;

    public FeedsAdapter(Context context,
                        ArrayList<Feeds> listPosts,
                        MainContract.OnItemClickListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_feeds, parent, false);
        return new FeedsViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        Feeds data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<Feeds> data) {
        mListData = data;
    }
}


class FeedsViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<Feeds>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private Feeds mData;

    private MainContract.OnItemClickListener mListener;

    public FeedsViewHolder(Context context, View itemView,
                           MainContract.OnItemClickListener listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }

    public FeedsViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private TextView tvTitle;

    private void initView() {

        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
    }

    private void initListener() {
        tvTitle.setOnClickListener(this);
    }

    @Override
    public void apply(Feeds data, int position) {
        mData = data;
        mPosition = position;

        tvTitle.setText(data.getTitle());

    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick(mPosition);
    }
}

