package com.asheslife.framework.sample;

import android.os.Bundle;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseActivity;
import com.asheslife.framework.utils.L;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleListActivity1 extends BaseActivity {

    private SampleListFragment mSampleListFragment;

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
        if (savedInstanceState != null) {
            L.e("onCreate(savedInstanceState);" + savedInstanceState);
            mSampleListFragment = (SampleListFragment) getSupportFragmentManager().findFragmentByTag("SampleListFragment");
            L.e(mSampleListFragment.toString());
        } else {
            mSampleListFragment = new SampleListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mSampleListFragmentLayout, mSampleListFragment, "SampleListFragment").commit();
        }

    }
}
