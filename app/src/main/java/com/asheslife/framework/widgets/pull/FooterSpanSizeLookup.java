package com.asheslife.framework.widgets.pull;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private BaseListAdapter adapter;
    private int spanCount;

    public FooterSpanSizeLookup(BaseListAdapter adapter, int spanCount) {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isLoadMoreFooter(position) || adapter.isSectionHeader(position)) {
            return spanCount;
        }
        return 1;
    }
}
