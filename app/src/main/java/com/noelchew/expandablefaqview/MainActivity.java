package com.noelchew.expandablefaqview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context context;

    Button btnNonListExample, btnListViewExample, btnRecyclerViewExample, btnCustomRecyclerViewExample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        btnNonListExample = (Button) findViewById(R.id.button_non_list_example);
        btnListViewExample = (Button) findViewById(R.id.button_list_view_example);
        btnRecyclerViewExample = (Button) findViewById(R.id.button_recycler_view_example);
        btnCustomRecyclerViewExample = (Button) findViewById(R.id.button_custom_recycler_view_example);

        btnNonListExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NonListExampleActivity.class));
            }
        });

        btnListViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ListViewExampleActivity.class));
            }
        });

        btnRecyclerViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FaqRecyclerViewExampleActivity.class));
            }
        });

        btnCustomRecyclerViewExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RecyclerViewExampleActivity.class));
            }
        });
    }

}
