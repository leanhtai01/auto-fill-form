package com.leanhtai01.autofillform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiChoiceQuestion implements Serializable {
    private String question;
    private List<String> answers;

    public MultiChoiceQuestion(String question) {
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }
}
