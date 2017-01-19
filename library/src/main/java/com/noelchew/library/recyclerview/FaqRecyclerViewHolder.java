package com.noelchew.library.recyclerview;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.View;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.R;
import com.noelchew.library.model.FaqObject;

/**
 * Created by noelchew on 17/01/2017.
 */

public class FaqRecyclerViewHolder extends RecyclerView.ViewHolder {
    ExpandableFaqView expandableFaqView;

    public FaqRecyclerViewHolder(View v, ExpandableFaqView.FaqEventListener faqEventListener) {
        super(v);
        this.expandableFaqView = (ExpandableFaqView) v.findViewById(R.id.expandable_faq_view);
        this.expandableFaqView.setFaqEventListener(faqEventListener);
    }

    public void update(FaqObject faqObject, SparseBooleanArray collapsedStatus, SparseIntArray expandableHeight, int position) {
        this.expandableFaqView.setQuestion(faqObject.getQuestion(), collapsedStatus, expandableHeight, position);
        this.expandableFaqView.setAnswer(faqObject.getAnswer(), collapsedStatus, expandableHeight, position);
    }

    public void setQuestionText(String text, SparseBooleanArray collapsedStatus, SparseIntArray expandableHeight, int position) {
        this.expandableFaqView.setQuestion(text, collapsedStatus, expandableHeight, position);
    }

    public void setAnswerText(String text, SparseBooleanArray collapsedStatus, SparseIntArray expandableHeight, int position) {
        this.expandableFaqView.setAnswer(text, collapsedStatus, expandableHeight, position);
    }

    public void setQuestionBackgroundColor(int colorRes) {
        this.expandableFaqView.setQuestionBackgroundColor(colorRes);
    }

    public void setAnswerBackgroundColor(int colorRes) {
        this.expandableFaqView.setAnswerBackgroundColor(colorRes);
    }

    public void setQuestionTextSize(float textSize) {
        this.expandableFaqView.setQuestionTextSize(textSize);
    }

    public void setAnswerTextSize(float textSize) {
        this.expandableFaqView.setAnswerTextSize(textSize);
    }

    public void setQuestionTextSize(int unit, float textSize) {
        this.expandableFaqView.setQuestionTextSize(unit, textSize);
    }

    public void setAnswerTextSize(int unit, float textSize) {
        this.expandableFaqView.setAnswerTextSize(unit, textSize);
    }

    public void setQuestionTextColor(@ColorRes int colorRes) {
        this.expandableFaqView.setQuestionTextColor(colorRes);
    }

    public void setAnswerTextColor(@ColorRes int colorRes) {
        this.expandableFaqView.setAnswerTextColor(colorRes);
    }

    public void setQuestionUnderlined(boolean questionUnderlined) {
        this.expandableFaqView.setQuestionUnderlined(questionUnderlined);
    }

    public void setAnswerUnderlined(boolean answerUnderlined) {
        this.expandableFaqView.setAnswerUnderlined(answerUnderlined);
    }
    public void setQuestionBackgroundDrawable(@DrawableRes int drawableResourceId) {
        this.expandableFaqView.setQuestionBackgroundDrawable(drawableResourceId);
    }

    public void setAnswerBackgroundDrawable(@DrawableRes int drawableResourceId) {
        this.expandableFaqView.setAnswerBackgroundDrawable(drawableResourceId);
    }
}
