package com.asheslife.framework.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.asheslife.framework.R;
import com.asheslife.framework.api.Api;
import com.asheslife.framework.core.BaseListActivity;
import com.asheslife.framework.model.BaseModel;
import com.asheslife.framework.model.Benefit;
import com.asheslife.framework.widgets.pull.BaseViewHolder;
import com.asheslife.framework.widgets.pull.PullRecycler;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleListActivity extends BaseListActivity<Benefit> {

    private int page = 1;

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.app_name);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        super.setUpData(savedInstanceState);
        recycler.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

//    @Override
//    protected ILayoutManager getLayoutManager() {
//        MyGridLayoutManager layoutManager = new MyGridLayoutManager(getApplicationContext(), 3);
//        return layoutManager;
//    }

    //    @Override
//    protected ILayoutManager getLayoutManager() {
//        return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            page = 1;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<BaseModel<ArrayList<Benefit>>> call = api.defaultBenefits(20, page++);

        call.enqueue(new Callback<BaseModel<ArrayList<Benefit>>>() {
                         @Override
                         public void onResponse(Call<BaseModel<ArrayList<Benefit>>> call, Response<BaseModel<ArrayList<Benefit>>> response) {
                             if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                                 mDataList.clear();
                             }
                             if (response.body().results == null || response.body().results.size() == 0) {
                                 recycler.enableLoadMore(false);
                             } else {
                                 recycler.enableLoadMore(true);
                                 mDataList.addAll(response.body().results);
                                 adapter.notifyDataSetChanged();
                             }
                             recycler.onRefreshCompleted();
                         }

                         @Override
                         public void onFailure(Call<BaseModel<ArrayList<Benefit>>> call, Throwable t) {
                             recycler.onRefreshCompleted();
                         }
                     }
        );

    }

    class SampleViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.mSampleListItemImg);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setVisibility(View.GONE);
            Glide.with(mSampleListItemImg.getContext())
                    .load(mDataList.get(position).url)
                    .centerCrop()
                    .placeholder(R.color.app_primary_color)
                    .crossFade()
                    .into(mSampleListItemImg);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }
}
