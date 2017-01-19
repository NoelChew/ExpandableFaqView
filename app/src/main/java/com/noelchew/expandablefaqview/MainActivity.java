package com.noelchew.expandablefaqview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.noelchew.library.ExpandableFaqView;
import com.noelchew.library.model.FaqObject;
import com.noelchew.library.recyclerview.FaqRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    ExpandableFaqView expandableFaqView, expandableFaqView2;

    ArrayList<FaqObject> faqObjectArrayList;

    FaqRecyclerView recyclerView;
    CustomFaqAdapter adapter;
//    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        expandableFaqView = (ExpandableFaqView) findViewById(R.id.expandable_faq_view_1);

        expandableFaqView2 = (ExpandableFaqView) findViewById(R.id.expandable_faq_view_2);
        expandableFaqView2.setQuestionBackgroundColor(android.R.color.holo_blue_bright);
        expandableFaqView2.setAnswerBackgroundColor(android.R.color.holo_green_light);
        expandableFaqView2.setQuestionTextColor(R.color.colorPrimary);
        expandableFaqView2.setAnswerTextColor(R.color.colorAccent);
        expandableFaqView2.setQuestion("Question 123123123");
        expandableFaqView2.setAnswer("Answer 123\n123\n123");
        expandableFaqView2.setQuestionUnderlined(true);
        expandableFaqView2.setAnimationDuration(1000);

        faqObjectArrayList = getDummyFaqList();

        adapter = new CustomFaqAdapter(faqObjectArrayList);
        recyclerView = (FaqRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

//        listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(new FaqListViewAdapter(context, faqObjectArrayList));
    }

    private ArrayList<FaqObject> getDummyFaqList() {
        ArrayList<FaqObject> faqObjectArrayList = new ArrayList<>();
        faqObjectArrayList.add(new FaqObject("1 Line", "1"));
        faqObjectArrayList.add(new FaqObject("2 Lines", "1\n2"));
        faqObjectArrayList.add(new FaqObject("3 Lines", "1\n2\n3"));
        faqObjectArrayList.add(new FaqObject("4 Lines", "1\n2\n3\n4"));
        faqObjectArrayList.add(new FaqObject("5 Lines", "1\n2\n3\n4\n5"));
        faqObjectArrayList.add(new FaqObject("6 Lines", "1\n2\n3\n4\n5\n6"));
        faqObjectArrayList.add(new FaqObject("7 Lines", "1\n2\n3\n4\n5\n6\n7"));
        faqObjectArrayList.add(new FaqObject("8 Lines", "1\n2\n3\n4\n5\n6\n7\n8"));

        return faqObjectArrayList;
    }

}
