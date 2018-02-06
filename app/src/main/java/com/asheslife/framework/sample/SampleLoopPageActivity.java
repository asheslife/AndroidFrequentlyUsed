package com.asheslife.framework.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseActivity;
import com.asheslife.framework.utils.L;
import com.asheslife.framework.widgets.LoopViewPager;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleLoopPageActivity extends BaseActivity {
    private LoopViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_loop_page);
    }

    @Override
    protected void setUpView() {
        viewPager = (LoopViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            L.e("getItem:" + position);
            position = (5 + position % 5) % 5;
            return SampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
}
