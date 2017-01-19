package com.noelchew.expandablefaqview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;

import com.noelchew.library.ExpandableFaqView;

/**
 * Created by noelchew on 19/01/2017.
 */

public class NonListExampleActivity extends AppCompatActivity {

    private static final String TAG = "NonListExampleActivity";

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_list_example);
        context = this;

        ExpandableFaqView expandableFaqView1 = (ExpandableFaqView) findViewById(R.id.expandable_faq_view_1);
        ExpandableFaqView expandableFaqView2 = (ExpandableFaqView) findViewById(R.id.expandable_faq_view_2);
        expandableFaqView2.setQuestion("Question 2");
        expandableFaqView2.setAnswer("These configurations are set in program code.");
        expandableFaqView2.setQuestionBackgroundColor(android.R.color.darker_gray);
        expandableFaqView2.setQuestionTextColor(android.R.color.white);
        expandableFaqView2.setAnswerBackgroundColor(R.color.cyan);
        expandableFaqView2.setAnswerTextColor(android.R.color.black);
        expandableFaqView2.setAnimationDuration(500);
        expandableFaqView2.setQuestionTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        expandableFaqView2.setAnswerTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        expandableFaqView2.setQuestionUnderlined(false);
        expandableFaqView2.setAnswerUnderlined(true);

        getSupportActionBar().setTitle(R.string.non_list_example_title);
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
