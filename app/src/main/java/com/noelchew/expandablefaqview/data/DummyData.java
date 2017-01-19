package com.noelchew.expandablefaqview.data;

import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class DummyData {

    public static ArrayList<FaqObject> getDummyData() {
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
