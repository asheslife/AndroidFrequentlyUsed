package com.asheslife.framework.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asheslife.framework.utils.L;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public abstract class BaseFragment extends Fragment {
    private boolean isVisibleToUser;
    private boolean isViewInitialized;
    private boolean isDataInitialized;
    private boolean isLazyLoadEnabled;

    public abstract void setUpView(View view);
    public abstract void setUpData();

    public void enableLazyLoad(){
        isLazyLoadEnabled = true;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.e(toString() + ":onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(toString() + ":onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        L.e(toString() + ":setUserVisibleHint:" + isVisibleToUser);
        checkIfLoadData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.e(toString() + ":onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.e(toString() + ":onViewCreated");
        if (!isLazyLoadEnabled){
            setUpView(view);
            setUpData();
        }else {
            setUpView(view);
            isViewInitialized = true;
            if (savedInstanceState != null){
                onRestoreInstanceState(savedInstanceState);
            }
            if (isDataInitialized){
                setUpData();
            }else {
                checkIfLoadData();
            }
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInitialized = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.e(toString() + ":onActivityCreated");

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        L.e(toString() + ":onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInitialized = false;
        L.e(toString() + ":onDestroyView");
    }

    private void checkIfLoadData() {
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            isDataInitialized = true;
//            TODO load data
            setUpData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        L.e(toString() + ":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e(toString() + ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e(toString() + ":onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        L.e(toString() + ":onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.e(toString() + ":onStop");
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(toString() + ":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.e(toString() + ":onDetach");
    }


    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        L.e(toString() + ":onInflate");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.e(toString() + ":onHiddenChanged:" + hidden);
    }

}
