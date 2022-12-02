package com.leanhtai01.autofillform.domain;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Quiz {
    private String url;
    private RemoteWebDriver driver;
    private List<WebElement> currentQuestionAndAnswersElements;
    private List<SingleChoiceQuestion> singleChoiceQuestions;

    public Quiz(String url, RemoteWebDriver driver) throws InterruptedException {
        this.url = url;
        this.driver = driver;

        getCurrentQuizStructure();
        getSingleChoiceQuestionsFromQuiz();
    }

    public void display() {
        int count = 1;
        for (var singleChoiceQuestion : singleChoiceQuestions) {
            System.console().printf("%d. %s%n", count, singleChoiceQuestion.getQuestion());
            for (var candidateAnswer : singleChoiceQuestion.getCandidateAnswers()) {
                System.console().printf("* %s%n", candidateAnswer);
            }

            count++;
            System.console().printf("%n");
        }
    }

    private void getCurrentQuizStructure() throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);

        String pathToQuestionAndAnswersElements = "//div[@class=\"office-form-question-content\"]";
        currentQuestionAndAnswersElements = driver.findElements(By.xpath(pathToQuestionAndAnswersElements));
    }

    private void getSingleChoiceQuestionsFromQuiz() {
        singleChoiceQuestions = new ArrayList<>();

        for (var questionAndAnswersElement : currentQuestionAndAnswersElements) {
            if (isSingleChoiceQuestion(questionAndAnswersElement)) {
                singleChoiceQuestions.add(getSingleChoiceQuestion(questionAndAnswersElement));
            }
        }
    }

    private SingleChoiceQuestion getSingleChoiceQuestion(WebElement questionAndAnswerElement) {
        String pathToQuestionElement = ".//div[@class=\"question-title-box\"]//span[@class=\"text-format-content\"]";
        WebElement questionElement = questionAndAnswerElement.findElement(By.xpath(pathToQuestionElement));
        String question = questionElement.getText();

        String pathToAnswerElements = ".//div[@class=\"office-form-question-choice\"]//input[@type=\"radio\"]";
        List<WebElement> answerElements = questionAndAnswerElement.findElements(By.xpath(pathToAnswerElements));
        List<String> candidateAnswers = new ArrayList<>();
        for (var answerElement : answerElements) {
            candidateAnswers.add(answerElement.getAttribute("value"));
        }

        return new SingleChoiceQuestion(question, candidateAnswers);
    }

    private boolean isSingleChoiceQuestion(WebElement questionAndAnswerElement) {
        String pathToAnswerElements = ".//div[@class=\"office-form-question-choice\"]//input";
        WebElement firstAnswerElement = questionAndAnswerElement.findElement(By.xpath(pathToAnswerElements));

        return firstAnswerElement.getAttribute("type").equals("radio");
    }
}
