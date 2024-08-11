package ru.netology.testing;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import ru.netology.testing.pages.MainPage;

public class TestInputText {

    private AppiumDriver driver;

    @Before
    public void createDriver() throws MalformedURLException {
//        String platform = System.getProperty("platform");
        String platform = "android";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if ("android".equals(platform)) {
            desiredCapabilities.setCapability(PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(DEVICE_NAME, "some name");
            desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        } else if ("ios".equals(platform)) {
            throw new IllegalArgumentException(String.format("The code for ios is not written yet"));
        } else {
            throw new IllegalArgumentException(String.format("Platform %s no supported", platform));
        }

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @Test
    public void testSpaceInputPage() {
        String spaceToInput = "\u0020";

        MainPage mainPage = new MainPage(driver);

        mainPage.textToBeChanged.isDisplayed();
        String expected = mainPage.textToBeChanged.getText();

        mainPage.userInputText.isDisplayed();
        mainPage.userInputText.sendKeys(spaceToInput);

        mainPage.buttonChangeText.isDisplayed();
        mainPage.buttonChangeText.click();

        mainPage.textToBeChanged.isDisplayed();
        assertEquals(expected, mainPage.textToBeChanged.getText());
    }

    @Test
    public void testInputTextNewActivity() {
        String textToInput = "testing Text";

        MainPage mainPage = new MainPage(driver);

        mainPage.userInputText.isDisplayed();
        mainPage.userInputText.sendKeys(textToInput);

        mainPage.buttonChangeText.isDisplayed();
        mainPage.buttonChangeText.click();

        String expected = mainPage.textToBeChanged.getText();

        mainPage.openTextNewActivity.isDisplayed();
        mainPage.openTextNewActivity.click();

        mainPage.textNewActivity.isDisplayed();

        String actual = mainPage.textNewActivity.getText();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
