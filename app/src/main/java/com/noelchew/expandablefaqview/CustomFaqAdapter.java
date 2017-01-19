package com.noelchew.expandablefaqview;

import com.noelchew.library.model.FaqObject;
import com.noelchew.library.recyclerview.FaqAdapter;
import com.noelchew.library.recyclerview.FaqRecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class CustomFaqAdapter extends FaqAdapter {
    public CustomFaqAdapter(ArrayList<FaqObject> faqObjectArrayList) {
        super(faqObjectArrayList);
    }

    @Override
    public void onBindViewHolder(FaqRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position % 2 == 0) {
            holder.setQuestionBackgroundColor(R.color.white);
            holder.setAnswerBackgroundColor(R.color.white);
        } else {
            holder.setQuestionBackgroundColor(R.color.cyan);
            holder.setAnswerBackgroundColor(R.color.cyan);
        }
    }
}
