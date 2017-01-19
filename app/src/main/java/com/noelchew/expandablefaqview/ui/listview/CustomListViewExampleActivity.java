package com.noelchew.expandablefaqview.ui.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.noelchew.expandablefaqview.R;
import com.noelchew.expandablefaqview.adapter.CustomFaqListViewAdapter;
import com.noelchew.expandablefaqview.data.DummyData;
import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.model.FaqObject;
import com.noelchew.library.util.ListViewScrollUtil;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class CustomListViewExampleActivity extends AppCompatActivity {

    private static final String TAG = "CustomListViewExampleActivity";

    Context context;

    ListView listView;
    CustomFaqListViewAdapter adapter;
    ArrayList<FaqObject> faqObjectsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_example);
        context = this;
        faqObjectsArrayList = DummyData.getDummyData();

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new CustomFaqListViewAdapter(context, faqObjectsArrayList);

        // this line to make sure listView scrolls to last item when last item is expanded
        adapter.setFaqEventListener(faqEventListener);
        listView.setAdapter(adapter);

        getSupportActionBar().setTitle(R.string.custom_list_view_example_title);
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
            listView.post(new Runnable() {
                @Override
                public void run() {
                    ListViewScrollUtil.smoothScrollItemToScreenBottom(listView, position, animationDuration);
                }
            });
        }
    };
}
