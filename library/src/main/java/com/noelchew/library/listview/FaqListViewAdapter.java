package com.noelchew.library.listview;

import android.content.Context;
import android.util.SparseBooleanArray;
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

    private final SparseBooleanArray mCollapsedStatus;

    public FaqListViewAdapter(Context context, ArrayList<FaqObject> faqObjects) {
        super(context, -1, faqObjects);
        mCollapsedStatus = new SparseBooleanArray();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_faq, parent, false);
            viewHolder = new ViewHolder((ExpandableFaqView) convertView.findViewById(R.id.expandable_faq_view));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FaqObject faqObject = getItem(position);
        viewHolder.expandableFaqView.setQuestion(faqObject.getQuestion(), mCollapsedStatus, position);
        viewHolder.expandableFaqView.setAnswer(faqObject.getAnswer(), mCollapsedStatus, position);

        return convertView;
    }

    private static class ViewHolder{
        ExpandableFaqView expandableFaqView;

        public ViewHolder(ExpandableFaqView expandableFaqView) {
            this.expandableFaqView = expandableFaqView;
        }
    }
}
