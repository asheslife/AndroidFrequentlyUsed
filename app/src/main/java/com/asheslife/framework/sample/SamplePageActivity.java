package com.asheslife.framework.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseActivity;
import com.asheslife.framework.utils.L;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SamplePageActivity extends BaseActivity {
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_page);
    }

    @Override
    protected void setUpView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            L.e("getItem:" + position);
            return SampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
