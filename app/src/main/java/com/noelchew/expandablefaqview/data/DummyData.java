package com.noelchew.expandablefaqview.data;

import com.noelchew.library.model.FaqObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 19/01/2017.
 */

public class DummyData {

    public static ArrayList<FaqObject> getDummyData() {
        ArrayList<FaqObject> faqObjectArrayList = new ArrayList<>();

        faqObjectArrayList.add(new FaqObject(
                "1 Line",
                "1"));
        faqObjectArrayList.add(new FaqObject(
                "2 Lines\n" +
                        "2 Lines",
                "1\n" +
                        "2"));
        faqObjectArrayList.add(new FaqObject(
                "3 Lines\n" +
                        "3 Lines\n" +
                        "3 Lines",
                "1\n" +
                        "2\n" +
                        "3"));
        faqObjectArrayList.add(new FaqObject(
                "4 Lines\n" +
                        "4 Lines\n" +
                        "4 Lines\n" +
                        "4 Lines",
                "1\n" +
                        "2\n" +
                        "3\n" +
                        "4"));
        faqObjectArrayList.add(new FaqObject(
                "5 Lines\n" +
                        "5 Lines\n" +
                        "5 Lines\n" +
                        "5 Lines\n" +
                        "5 Lines",
                "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5"));
        faqObjectArrayList.add(new FaqObject(
                "6 Lines\n" +
                        "6 Lines\n" +
                        "6 Lines\n" +
                        "6 Lines\n" +
                        "6 Lines\n" +
                        "6 Lines",
                "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n" +
                        "6"));
        faqObjectArrayList.add(new FaqObject(
                "7 Lines\n" +
                        "7 Lines\n" +
                        "7 Lines\n" +
                        "7 Lines\n" +
                        "7 Lines\n" +
                        "7 Lines\n" +
                        "7 Lines",
                "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n" +
                        "6\n" +
                        "7"));
        faqObjectArrayList.add(new FaqObject(
                "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines\n" +
                        "8 Lines",
                "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n" +
                        "6\n" +
                        "7\n" +
                        "8"));

        return faqObjectArrayList;
    }
}
