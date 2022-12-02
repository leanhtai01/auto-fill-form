package com.leanhtai01.autofillform.domain;

import java.util.List;

public class SingleChoiceQuestion {
    private String question;
    private List<String> candidateAnswers;
    private String answer;

    public SingleChoiceQuestion(String question, List<String> candidateAnswers) {
        this.question = question;
        this.candidateAnswers = candidateAnswers;
        this.answer = null;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
