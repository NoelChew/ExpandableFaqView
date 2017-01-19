package com.noelchew.expandablefaqview.ui.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.noelchew.expandablefaqview.R;
import com.noelchew.expandablefaqview.adapter.CustomFaqRecyclerViewAdapter;
import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class CustomRecyclerViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewExampleActivity";

    Context context;

    RecyclerView recyclerView;
    ArrayList<FaqObject> faqObjectsArrayList;

    CustomFaqRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recycler_view_example);
        context = this;
        faqObjectsArrayList = DummyData.getDummyData();

        adapter = new CustomFaqRecyclerViewAdapter(faqObjectsArrayList);
        // this line to make sure recyclerView scrolls to last item when last item is expanded
        adapter.setFaqEventListener(faqEventListener);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setTitle(R.string.custom_recycler_view_example_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    ExpandableFaqView.FaqEventListener faqEventListener = new ExpandableFaqView.FaqEventListener() {
        @Override
        public void onAnswerExpanded(final int position, final int animationDuration) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.smoothScrollToPosition(position);
                }
            });
        }
    };
}
