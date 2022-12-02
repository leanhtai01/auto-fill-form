package com.leanhtai01.autofillform;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.leanhtai01.autofillform.domain.Form;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/leanhtai01/chromedriver_linux64/chromedriver");

        String url = "https://forms.office.com/r/sGry7hWTHU";
        ChromeOptions options = new ChromeOptions()
                .addArguments("user-data-dir=/home/leanhtai01/.config/google-chrome/Default");
        ChromeDriver driver = new ChromeDriver(options);

        Form form = new Form(url, driver);
        System.console().printf("%s%n", form.getQuiz());
        form.fillForm();
    }
}
