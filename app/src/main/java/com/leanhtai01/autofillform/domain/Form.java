package com.leanhtai01.autofillform.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Form {
    private String url;
    private RemoteWebDriver driver;
    private String pathToQuizFile;
    private Quiz quiz;
    private List<WebElement> questionAndAnswersElements;

    public Form(String url, RemoteWebDriver driver) throws InterruptedException {
        this.url = url;
        this.driver = driver;
        this.pathToQuizFile = "app/src/main/resources/quiz.dat";
        this.quiz = new Quiz();

        getQuestionAndAnswersElements();

        try {
            readQuizFromFile();
        } catch (Exception e) {
            getQuizFromForm();
        }
    }

    public Quiz getQuiz() {
        return quiz;
    }

    private void readQuizFromFile() throws ClassNotFoundException, IOException {
        try (var reader = new ObjectInputStream(new FileInputStream(pathToQuizFile))) {
            var dataFromFile = (Quiz) reader.readObject();

            quiz = dataFromFile;
        }
    }

    private void writeQuizToFile() throws IOException {
        try (var writer = new ObjectOutputStream(new FileOutputStream(pathToQuizFile))) {
            writer.writeObject(quiz);
        }
    }

    private void getQuestionAndAnswersElements() throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);

        String pathToQuestionAndAnswersElements = "//div[@class=\"office-form-question-content\"]";
        questionAndAnswersElements = driver.findElements(By.xpath(pathToQuestionAndAnswersElements));
    }

    private void getQuizFromForm() {
        for (var questionAndAnswersElement : questionAndAnswersElements) {
            if (isSingleChoiceQuestion(questionAndAnswersElement)) {
                quiz.addSingleChoiceQuestion(getSingleChoiceQuestion(questionAndAnswersElement));
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
