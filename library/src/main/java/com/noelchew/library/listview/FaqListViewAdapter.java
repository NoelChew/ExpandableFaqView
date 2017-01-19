package com.noelchew.library.listview;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.R;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 18/01/2017.
 */

public class FaqListViewAdapter extends ArrayAdapter<FaqObject> {

    protected final SparseBooleanArray mCollapsedStatus;
    protected final SparseIntArray mExpandableHeight;
    protected ExpandableFaqView.FaqEventListener faqEventListener;
    int layoutId;

    public FaqListViewAdapter(Context context, ArrayList<FaqObject> faqObjects) {
        super(context, -1, faqObjects);
        mCollapsedStatus = new SparseBooleanArray();
        mExpandableHeight = new SparseIntArray();
    }

    public FaqListViewAdapter(Context context, int layoutId, ArrayList<FaqObject> faqObjects) {
        super(context, -1, faqObjects);
        mCollapsedStatus = new SparseBooleanArray();
        mExpandableHeight = new SparseIntArray();
        this.layoutId = layoutId;
    }

    public void setFaqEventListener(ExpandableFaqView.FaqEventListener faqEventListener) {
        this.faqEventListener = faqEventListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            if (layoutId == 0) {
                layoutId = R.layout.list_item_faq;
            }
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder((ExpandableFaqView) convertView.findViewById(R.id.expandable_faq_view), this.faqEventListener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FaqObject faqObject = getItem(position);
        viewHolder.expandableFaqView.setQuestion(faqObject.getQuestion(), mCollapsedStatus, mExpandableHeight, position);
        viewHolder.expandableFaqView.setAnswer(faqObject.getAnswer(), mCollapsedStatus, mExpandableHeight, position);

        return convertView;
    }

//    // ------ this is not a good practice -------
//    // doing this means it will not recycle the views
//    @Override
//    public int getViewTypeCount() {
//        return getCount();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//    // -------------------------------------------

    protected static class ViewHolder {
        protected ExpandableFaqView expandableFaqView;

        public ViewHolder(ExpandableFaqView expandableFaqView, ExpandableFaqView.FaqEventListener faqEventListener) {
            this.expandableFaqView = expandableFaqView;
            this.expandableFaqView.setFaqEventListener(faqEventListener);
        }

        public ExpandableFaqView getExpandableFaqView() {
            return expandableFaqView;
        }

        public void setExpandableFaqView(ExpandableFaqView expandableFaqView) {
            this.expandableFaqView = expandableFaqView;
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
}
