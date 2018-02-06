package com.asheslife.framework.widgets.pull.layoutmanager;

import android.support.v7.widget.RecyclerView;

import com.asheslife.framework.widgets.pull.BaseListAdapter;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastVisiblePosition();
    void setUpAdapter(BaseListAdapter adapter);
}
