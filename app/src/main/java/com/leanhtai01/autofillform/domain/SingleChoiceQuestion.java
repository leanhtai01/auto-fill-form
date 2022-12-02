package com.leanhtai01.autofillform.domain;

import java.io.Serializable;
import java.util.List;

public class SingleChoiceQuestion implements Serializable {
    private String question;
    private List<String> candidateAnswers;
    private String previousAnswer;
    private String currentAnswer;

    public SingleChoiceQuestion(String question, List<String> candidateAnswers) {
        this.question = question;
        this.candidateAnswers = candidateAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getCandidateAnswers() {
        return candidateAnswers;
    }

    public void setCandidateAnswers(List<String> candidateAnswers) {
        this.candidateAnswers = candidateAnswers;
    }

    public String getPreviousAnswer() {
        return previousAnswer;
    }

    public void setPreviousAnswer(String previousAnswer) {
        this.previousAnswer = previousAnswer;
    }

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(String currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(question);
        for (String answer : candidateAnswers) {
            builder.append("\n* " + answer);
        }

        return builder.toString();
    }
}
