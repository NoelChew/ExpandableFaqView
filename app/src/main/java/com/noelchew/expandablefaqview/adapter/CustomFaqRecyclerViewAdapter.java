package com.noelchew.expandablefaqview.adapter;

import com.noelchew.expandablefaqview.R;
import com.noelchew.library.model.FaqObject;
import com.noelchew.library.recyclerview.FaqRecyclerViewAdapter;
import com.noelchew.library.recyclerview.FaqRecyclerViewHolder;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class CustomFaqRecyclerViewAdapter extends FaqRecyclerViewAdapter {
    public CustomFaqRecyclerViewAdapter(ArrayList<FaqObject> faqObjectArrayList) {
        // use customised layout
        super(R.layout.custom_faq_layout, faqObjectArrayList);
    }

    @Override
    public void onBindViewHolder(FaqRecyclerViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position);

        // Override with own actions as below:
        holder.setQuestionText(getItem(position).getQuestion(), mCollapsedStatus, mExpandableHeight, position);
        holder.setAnswerText(getItem(position).getAnswer(), mCollapsedStatus, mExpandableHeight, position);
        if (position % 2 != 0) {
            holder.setQuestionBackgroundColor(R.color.colorPrimaryDark);
            holder.setAnswerBackgroundColor(R.color.colorPrimary);
            holder.setQuestionTextColor(R.color.white);
            holder.setAnswerTextColor(R.color.white);
        } else {
            holder.setQuestionBackgroundColor(R.color.colorAccent);
            holder.setAnswerBackgroundColor(R.color.colorAccentLight);
            holder.setQuestionTextColor(android.R.color.black);
            holder.setAnswerTextColor(android.R.color.black);
        }
    }
}
