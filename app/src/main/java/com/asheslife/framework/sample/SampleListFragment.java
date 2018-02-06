package com.asheslife.framework.sample;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.asheslife.framework.R;
import com.asheslife.framework.api.Api;
import com.asheslife.framework.constants.ConstantValues;
import com.asheslife.framework.core.BaseFragment;
import com.asheslife.framework.core.BaseListFragment;
import com.asheslife.framework.core.ITabFragment;
import com.asheslife.framework.model.BaseModel;
import com.asheslife.framework.model.Benefit;
import com.asheslife.framework.widgets.pull.BaseViewHolder;
import com.asheslife.framework.widgets.pull.PullRecycler;
import com.asheslife.framework.widgets.pull.layoutmanager.ILayoutManager;
import com.asheslife.framework.widgets.pull.layoutmanager.MyGridLayoutManager;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleListFragment extends BaseListFragment<Benefit> implements ITabFragment {
    private int page = 1;

    public static SampleListFragment newInstance(int page) {
        SampleListFragment fragment = new SampleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValues.KEY_FRAGMENT_PAGE, page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        enableLazyLoad();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyGridLayoutManager(getContext(), 3);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    public void setUpData() {
        if (mDataList == null) {
           recycler.setRefreshing();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ConstantValues.KEY_SAVE_LIST, mDataList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mDataList = (ArrayList<Benefit>) savedInstanceState.getSerializable(ConstantValues.KEY_SAVE_LIST);
    }

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.rxBenefits(20, page++)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseModel<ArrayList<Benefit>>>() {
                    @Override
                    public void call(BaseModel<ArrayList<Benefit>> model) {
                        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                            mDataList.clear();
                        }
                        if (model.results == null || model.results.size() == 0) {
                            recycler.enableLoadMore(false);
                        } else {
                            recycler.enableLoadMore(true);
                            mDataList.addAll(model.results);
                            adapter.notifyDataSetChanged();
                        }
                        recycler.onRefreshCompleted();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        recycler.onRefreshCompleted();
                    }
                })
        ;
    }

    @Override
    public void onMenuItemClick() {

    }

    @Override
    public BaseFragment getFragment() {
        return this;
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
