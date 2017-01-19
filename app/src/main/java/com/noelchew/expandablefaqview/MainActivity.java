package com.noelchew.expandablefaqview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.noelchew.expandablefaqview.ui.listview.CustomListViewExampleActivity;
import com.noelchew.expandablefaqview.ui.listview.FaqListViewExampleActivity;
import com.noelchew.expandablefaqview.ui.recyclerview.CustomRecyclerViewExampleActivity;
import com.noelchew.expandablefaqview.ui.recyclerview.FaqRecyclerViewExampleActivity;

public class MainActivity extends AppCompatActivity {

    Context context;

    Button btnNonListExample, btnFaqListViewExample, btnCustomListViewExample, btnFaqRecyclerViewExample, btnCustomRecyclerViewExample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        btnNonListExample = (Button) findViewById(R.id.button_non_list_example);
        btnFaqListViewExample = (Button) findViewById(R.id.button_faq_list_view_example);
        btnCustomListViewExample = (Button) findViewById(R.id.button_custom_list_view_example);
        btnFaqRecyclerViewExample = (Button) findViewById(R.id.button_faq_recycler_view_example);
        btnCustomRecyclerViewExample = (Button) findViewById(R.id.button_custom_recycler_view_example);

        btnNonListExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NonListExampleActivity.class));
            }
        });

        btnFaqListViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FaqListViewExampleActivity.class));
            }
        });

        btnCustomListViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CustomListViewExampleActivity.class));
            }
        });

        btnFaqRecyclerViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FaqRecyclerViewExampleActivity.class));
            }
        });

        btnCustomRecyclerViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CustomRecyclerViewExampleActivity.class));
            }
        });
    }

}
