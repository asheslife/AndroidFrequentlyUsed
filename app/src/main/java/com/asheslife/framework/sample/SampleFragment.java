package com.asheslife.framework.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asheslife.framework.constants.ConstantValues;
import com.asheslife.framework.core.BaseFragment;
import com.asheslife.framework.utils.L;

import java.util.Random;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class SampleFragment extends BaseFragment {

    private TextView label;
    private int page = -1;
    private int param = -1;

    public static SampleFragment newInstance(int page) {
        SampleFragment fragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValues.KEY_FRAGMENT_PAGE, page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        page = getArguments().getInt(ConstantValues.KEY_FRAGMENT_PAGE);
        enableLazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.e(toString() + ":onCreateView");
        label = new TextView(getContext());
        label.setText("page:" + page);
        return label;
    }

    @Override
    public void setUpView(View view) {

    }

    @Override
    public void setUpData() {

        if (param == -1) {
            param = new Random().nextInt(10);
        }
        label.append("load data:" + param);

//        load data
//        bind data
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("param", param);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            param = savedInstanceState.getInt("param");
        }
    }

    @Override
    public String toString() {
        return "-------page : " + page;
    }
}
