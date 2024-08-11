package ru.netology.testing.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MainPage {
    @FindBy(id = "textToBeChanged")
    public MobileElement textToBeChanged;

    @FindBy(id = "userInput")
    public MobileElement userInputText;

    @FindBy(id = "buttonChange")
    public MobileElement buttonChangeText;

    @FindBy(id = "buttonActivity")
    public MobileElement openTextNewActivity;

    @FindBy(id = "text")
    public MobileElement textNewActivity;

    private AppiumDriver driver;

    public MainPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
}