package com.asheslife.framework.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseSectionListActivity;
import com.asheslife.framework.widgets.pull.BaseViewHolder;
import com.asheslife.framework.widgets.pull.PullRecycler;
import com.asheslife.framework.widgets.pull.layoutmanager.ILayoutManager;
import com.asheslife.framework.widgets.pull.layoutmanager.MyLinearLayoutManager;
import com.asheslife.framework.widgets.pull.section.SectionData;

import java.util.ArrayList;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleSectionListActivity extends BaseSectionListActivity<String> {

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
    protected BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getApplicationContext());
        return layoutManager;
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
