package com.noelchew.library.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.noelchew.library.ExpandableFaqView;

/**
 * Created by noelchew on 18/01/2017.
 */

public class FaqRecyclerView extends RecyclerView {
    public FaqRecyclerView(Context context) {
        super(context);
    }

    public FaqRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FaqRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(FaqRecyclerViewAdapter adapter) {
        adapter.setFaqEventListener(faqEventListener);
        super.setAdapter(adapter);
    }

    ExpandableFaqView.FaqEventListener faqEventListener = new ExpandableFaqView.FaqEventListener() {
        @Override
        public void onAnswerExpanded(final int position, final int animationDuration) {
            FaqRecyclerView.this.post(new Runnable() {
                @Override
                public void run() {
                    FaqRecyclerView.this.smoothScrollToPosition(position);
                }
            });
        }
    };
}
