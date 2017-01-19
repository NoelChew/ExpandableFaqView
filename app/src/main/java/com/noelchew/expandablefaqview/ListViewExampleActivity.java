package com.noelchew.expandablefaqview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.listview.FaqListViewAdapter;
import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class ListViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "ListViewExampleActivity";

    Context context;

    ListView listView;
    ArrayList<FaqObject> faqObjectsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_example);
        context = this;

        listView = (ListView) findViewById(R.id.list_view);
        faqObjectsArrayList = DummyData.getDummyData();

        listView.setAdapter(new FaqListViewAdapter(context, faqObjectsArrayList));

        getSupportActionBar().setTitle(R.string.list_view_example_title);
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
