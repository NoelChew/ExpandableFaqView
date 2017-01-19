package com.noelchew.expandablefaqview.ui.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.noelchew.expandablefaqview.R;
import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.model.FaqObject;
import com.noelchew.library.recyclerview.FaqRecyclerView;
import com.noelchew.library.recyclerview.FaqRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class FaqRecyclerViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "FaqRecyclerViewExampleActivity";

    Context context;

    FaqRecyclerView faqRecyclerView;
    ArrayList<FaqObject> faqObjectsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_recycler_view_example);
        context = this;
        faqObjectsArrayList = DummyData.getDummyData();

        faqRecyclerView = (FaqRecyclerView) findViewById(R.id.faq_recycler_view);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        faqRecyclerView.setAdapter(new FaqRecyclerViewAdapter(faqObjectsArrayList));

        getSupportActionBar().setTitle(R.string.faq_recycler_view_example_title);
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
}
