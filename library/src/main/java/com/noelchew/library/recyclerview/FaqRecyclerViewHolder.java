package com.noelchew.library.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.R;
import com.noelchew.library.model.FaqObject;

/**
 * Created by noelchew on 17/01/2017.
 */

public class FaqRecyclerViewHolder extends RecyclerView.ViewHolder {
    ExpandableFaqView expandableFaqView;

    public FaqRecyclerViewHolder(View v) {
        super(v);
        this.expandableFaqView = (ExpandableFaqView) v.findViewById(R.id.expandable_faq_view);
    }

    public void update(FaqObject faqObject, SparseBooleanArray collapsedStatus, int position) {
        this.expandableFaqView.setQuestion(faqObject.getQuestion(), collapsedStatus, position);
        this.expandableFaqView.setAnswer(faqObject.getAnswer(), collapsedStatus, position);
    }

    public void setQuestionBackgroundColor(int colorRes) {
        this.expandableFaqView.setQuestionBackgroundColor(colorRes);
    }

    public void setAnswerBackgroundColor(int colorRes) {
        this.expandableFaqView.setAnswerBackgroundColor(colorRes);
    }
}
