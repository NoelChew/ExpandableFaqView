package com.noelchew.library.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by noelchew on 18/01/2017.
 */

public class FaqLayoutManager extends LinearLayoutManager {
    public FaqLayoutManager(Context context) {
        super(context);
        this.setRecycleChildrenOnDetach(false);
    }
}
