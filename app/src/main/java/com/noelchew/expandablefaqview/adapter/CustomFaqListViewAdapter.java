package com.noelchew.expandablefaqview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noelchew.expandablefaqview.R;
import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.listview.FaqListViewAdapter;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class CustomFaqListViewAdapter extends FaqListViewAdapter {


    public CustomFaqListViewAdapter(Context context, ArrayList<FaqObject> faqObjectArrayList) {
        super(context, faqObjectArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_faq_layout, parent, false);
            viewHolder = new ViewHolder((ExpandableFaqView) convertView.findViewById(R.id.expandable_faq_view), this.faqEventListener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Override with own actions as below:
        viewHolder.setQuestionText(getItem(position).getQuestion(), mCollapsedStatus, mExpandableHeight, position);
        viewHolder.setAnswerText(getItem(position).getAnswer(), mCollapsedStatus, mExpandableHeight, position);
        if (position % 2 != 0) {
            viewHolder.setQuestionBackgroundColor(R.color.colorPrimaryDark);
            viewHolder.setAnswerBackgroundColor(R.color.colorPrimary);
            viewHolder.setQuestionTextColor(R.color.white);
            viewHolder.setAnswerTextColor(R.color.white);
        } else {
            viewHolder.setQuestionBackgroundColor(R.color.colorAccent);
            viewHolder.setAnswerBackgroundColor(R.color.colorAccentLight);
            viewHolder.setQuestionTextColor(android.R.color.black);
            viewHolder.setAnswerTextColor(android.R.color.black);
        }

        return convertView;
    }
}
