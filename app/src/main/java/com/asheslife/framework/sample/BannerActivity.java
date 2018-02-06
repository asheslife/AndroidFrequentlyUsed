package com.asheslife.framework.sample;

import android.os.Bundle;

import com.asheslife.framework.R;
import com.asheslife.framework.core.BaseActivity;
import com.asheslife.framework.widgets.banner.BannerView;

import java.util.ArrayList;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class BannerActivity extends BaseActivity implements BannerView.OnBannerItemClickListener {

    private BannerView mBannerView;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_banner);
    }

    @Override
    protected void setUpView() {
        mBannerView = (BannerView) findViewById(R.id.mBannerView);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        ArrayList<BannerView.Banner> banners = new ArrayList<>();
        banners.add(new BannerView.Banner(R.drawable.ic_tab_contact));
        banners.add(new BannerView.Banner(R.drawable.ic_tab_msg));
        banners.add(new BannerView.Banner(R.drawable.ic_tab_moments));
        banners.add(new BannerView.Banner(R.drawable.ic_tab_profile));
        mBannerView.setUpData(banners, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBannerView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBannerView.onStop();
    }

    @Override
    public void onBannerClick(int index, BannerView.Banner banner) {

    }
}
