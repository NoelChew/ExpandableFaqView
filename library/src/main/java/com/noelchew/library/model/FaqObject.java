package com.noelchew.library.model;

/**
 * Created by noelchew on 17/01/2017.
 */

public class FaqObject {
    private String question;
    private String answer;

    public FaqObject(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
