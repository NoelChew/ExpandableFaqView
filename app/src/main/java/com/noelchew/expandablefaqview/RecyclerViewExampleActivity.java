package com.noelchew.expandablefaqview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.noelchew.expandablefaqview.adapter.CustomFaqAdapter;
import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class RecyclerViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewExampleActivity";

    Context context;

    RecyclerView recyclerView;
    ArrayList<FaqObject> faqObjectsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // must add this line to ensure expand/collapse persistence
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        faqObjectsArrayList = DummyData.getDummyData();

        recyclerView.setAdapter(new CustomFaqAdapter(faqObjectsArrayList));

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
}
