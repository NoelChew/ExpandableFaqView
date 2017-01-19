package com.noelchew.library.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noelchew.library.R;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 17/01/2017.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqRecyclerViewHolder> {
    private final SparseBooleanArray mCollapsedStatus;
    protected ArrayList<FaqObject> faqObjectArrayList;
    int layoutId;

    public FaqAdapter(ArrayList<FaqObject> faqObjectArrayList) {
        this.faqObjectArrayList = faqObjectArrayList;
        mCollapsedStatus = new SparseBooleanArray();
    }

    public FaqAdapter(int layoutId, ArrayList<FaqObject> faqObjectArrayList) {
        this.faqObjectArrayList = faqObjectArrayList;
        mCollapsedStatus = new SparseBooleanArray();
        this.layoutId = layoutId;
    }

    @Override
    public FaqRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        if (layoutId == 0) {
            layoutId = R.layout.list_item_faq;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        FaqRecyclerViewHolder vh = new FaqRecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FaqRecyclerViewHolder holder, int position) {
        holder.update(faqObjectArrayList.get(position), mCollapsedStatus, position);
    }

    @Override
    public int getItemCount() {
        return faqObjectArrayList == null ? 0 : faqObjectArrayList.size();
    }
}
