package com.asheslife.framework.sample;

import android.os.Bundle;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseActivity;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleSectionListActivity1 extends BaseActivity {

    private SampleSectionListFragment mSampleListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_list_1, R.string.app_name);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mSampleListFragment = new SampleSectionListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mSampleListFragmentLayout, mSampleListFragment).commit();
    }
}
