package com.asheslife.framework.sample;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asheslife.framework.R;
import com.asheslife.framework.constants.ConstantValues;
import com.asheslife.framework.core.BaseFragment;
import com.asheslife.framework.core.BaseSectionListFragment;
import com.asheslife.framework.core.ITabFragment;
import com.asheslife.framework.utils.L;
import com.asheslife.framework.widgets.pull.BaseViewHolder;
import com.asheslife.framework.widgets.pull.PullRecycler;
import com.asheslife.framework.widgets.pull.layoutmanager.ILayoutManager;
import com.asheslife.framework.widgets.pull.layoutmanager.MyGridLayoutManager;
import com.asheslife.framework.widgets.pull.section.SectionData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleSectionListFragment extends BaseSectionListFragment<String> implements ITabFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        enableLazyLoad();
    }

    @Override
    protected BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
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
        mDataList = (ArrayList<SectionData<String>>) savedInstanceState.getSerializable(ConstantValues.KEY_SAVE_LIST);
    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                int size = mDataList.size();
                mDataList.add(new SectionData(true, size, "header " + size));
                for (int i = size; i < size + 20; i++) {
                    mDataList.add(new SectionData("sample list item" + i));
                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if (mDataList.size() < 100) {
                    recycler.enableLoadMore(true);
                } else {
                    recycler.enableLoadMore(false);
                }
            }
        }, 3000);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMenuItemClick() {
        L.e("onMenuItemClick by listener");
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }

    class SampleViewHolder extends BaseViewHolder {

        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(mDataList.get(position).t);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }
}
