package com.leanhtai01.autofillform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {
    private List<SingleChoiceQuestion> singleChoiceQuestions;
    private int score;

    public Quiz() {
        this.singleChoiceQuestions = new ArrayList<>();
        this.score = 0;
    }

    public List<SingleChoiceQuestion> getSingleChoiceQuestions() {
        return singleChoiceQuestions;
    }

    public void setSingleChoiceQuestions(List<SingleChoiceQuestion> singleChoiceQuestions) {
        this.singleChoiceQuestions = singleChoiceQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addSingleChoiceQuestion(SingleChoiceQuestion question) {
        singleChoiceQuestions.add(question);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < singleChoiceQuestions.size(); i++) {
            builder.append(singleChoiceQuestions.get(i) + "\n\n");
        }

        builder.append("Score: " + score + "/" + singleChoiceQuestions.size());

        return builder.toString();
    }
}
