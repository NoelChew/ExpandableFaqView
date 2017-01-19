package com.noelchew.library.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.util.ListViewScrollUtil;

/**
 * Created by noelchew on 19/01/2017.
 */

public class FaqListView extends ListView {
    public FaqListView(Context context) {
        super(context);
    }

    public FaqListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FaqListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(FaqListViewAdapter adapter) {
        adapter.setFaqEventListener(faqEventListener);
        super.setAdapter(adapter);
    }

    ExpandableFaqView.FaqEventListener faqEventListener = new ExpandableFaqView.FaqEventListener() {
        @Override
        public void onAnswerExpanded(final int position, final int animationDuration) {
            FaqListView.this.post(new Runnable() {
                @Override
                public void run() {
                    ListViewScrollUtil.smoothScrollItemToScreenBottom(FaqListView.this, position, animationDuration);
                }
            });
        }
    };
}
