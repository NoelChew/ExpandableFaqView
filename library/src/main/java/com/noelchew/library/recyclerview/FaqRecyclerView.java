package com.noelchew.library.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by noelchew on 18/01/2017.
 */

public class FaqRecyclerView extends RecyclerView {
    public FaqRecyclerView(Context context) {
        super(context);
        init();
    }

    public FaqRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FaqRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.getRecycledViewPool().setMaxRecycledViews(0, 0);
    }
}
