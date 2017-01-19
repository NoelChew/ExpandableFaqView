package com.noelchew.expandablefaqview.ui.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.noelchew.expandablefaqview.R;
import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.listview.FaqListView;
import com.noelchew.library.listview.FaqListViewAdapter;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class FaqListViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "FaqListViewExampleActivity";

    Context context;

    FaqListView faqListView;
    ArrayList<FaqObject> faqObjectsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_list_view_example);
        context = this;
        faqObjectsArrayList = DummyData.getDummyData();

        faqListView = (FaqListView) findViewById(R.id.faq_list_view);
        faqListView.setAdapter(new FaqListViewAdapter(context, faqObjectsArrayList));

        getSupportActionBar().setTitle(R.string.faq_list_view_example_title);
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
