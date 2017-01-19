package com.noelchew.library.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.R;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 17/01/2017.
 */

public class FaqRecyclerViewAdapter extends RecyclerView.Adapter<FaqRecyclerViewHolder> {
    protected final SparseBooleanArray mCollapsedStatus;
    protected final SparseIntArray mExpandableHeight;
    protected ArrayList<FaqObject> faqObjectArrayList;
    protected ExpandableFaqView.FaqEventListener faqEventListener;
    int layoutId;

    public FaqRecyclerViewAdapter(ArrayList<FaqObject> faqObjectArrayList) {
        this.faqObjectArrayList = faqObjectArrayList;
        mCollapsedStatus = new SparseBooleanArray();
        mExpandableHeight = new SparseIntArray();
    }

    public FaqRecyclerViewAdapter(int layoutId, ArrayList<FaqObject> faqObjectArrayList) {
        this.faqObjectArrayList = faqObjectArrayList;
        mCollapsedStatus = new SparseBooleanArray();
        mExpandableHeight = new SparseIntArray();
        this.layoutId = layoutId;
    }

    public void setFaqEventListener(ExpandableFaqView.FaqEventListener faqEventListener) {
        this.faqEventListener = faqEventListener;
    }

    @Override
    public FaqRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        if (layoutId == 0) {
            layoutId = R.layout.list_item_faq;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        FaqRecyclerViewHolder vh = new FaqRecyclerViewHolder(v, this.faqEventListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(FaqRecyclerViewHolder holder, int position) {
        holder.update(faqObjectArrayList.get(position), mCollapsedStatus, mExpandableHeight, position);
    }

    @Override
    public int getItemCount() {
        return faqObjectArrayList == null ? 0 : faqObjectArrayList.size();
    }

    public FaqObject getItem(int position) {
        return faqObjectArrayList.get(position);
    }
}
