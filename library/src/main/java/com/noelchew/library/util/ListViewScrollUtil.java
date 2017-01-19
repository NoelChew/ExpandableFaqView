package com.noelchew.library.util;

/**
 * Created by noelchew on 19/01/2017.
 */

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;


/**
 * Created by noelchew on 19/01//2016.
 */

public class ListViewScrollUtil {
    public static void smoothScrollItemToScreenBottom(final AbsListView view, final int position, final int animationDuration) {
        if (view != null) {
            int firstVisible = view.getFirstVisiblePosition();
            int lastVisible = view.getLastVisiblePosition();

            if (position > lastVisible) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.smoothScrollToPosition(position);
                    }
                }, 100);
            } else {
                View child = view.getChildAt(position - firstVisible);
                final int offset = child.getTop() + child.getMeasuredHeight()
                        - view.getMeasuredHeight();
                if (offset > 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.smoothScrollBy(offset, animationDuration);
                        }
                    }, 100);
                }
            }
        }
    }

}