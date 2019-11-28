package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignInPage {
    private final WebDriverWait wait;

//    private final static By createAccountButton = By.xpath("//android.widget.Button[@content-desc = \"Create account\"]");
    private final static By createAccountButton = By.id("Create account");
    private final static By firstNameInput = By.id("firstName");
    private final static By lastNameInput = By.id("lastName");
    private final static By numberInput = By.id("phoneNumberId");
    private final static By nextButton = By.id("Next");
    private final static By countryInput = By.xpath("//android.widget.ListView[@content-desc=\"Select a country\"]/android.view.View[1]");
    private final static By month = By.id("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]");
    private final static By monthInput = By.id("month");
    private final static By dayInput = By.id("day");
    private final static By yearInput = By.id("year");
    private final static By genderInput = By.id("gender");
//    private final static By day = By.xpath();
//    private final static By year = By.xpath();
//    private final static By gender = By.xpath();

    public SignInPage(WebDriverWait wait) {
        this.wait = wait;
    }

    public void clickCreateAccountButton() {
        wait.until(visibilityOfElementLocated(createAccountButton)).click();
    }

    public void fillFirstNameInputByRandom() {
        String firstName = createRandomString();
        wait.until(visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
    }

    public void fillLastNameInputByRandom() {
        String lastName = createRandomString();
        wait.until(visibilityOfElementLocated(lastNameInput)).sendKeys(lastName);
    }

    private String createRandomString() {
        Random random = new Random();
        byte[] bytes = new byte[7];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) (97 + random.nextInt(25));

        return new String(bytes);
    }

    public void fillTelephoneNumber() {
        Random random = new Random();
        byte[] bytes = new byte[9];
        random.nextInt(10);
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) (48 + random.nextInt(10));

        String number = "+79" + new String(bytes);
        wait.until(visibilityOfElementLocated(numberInput)).sendKeys(number);
    }

    public void clickNextButton() {
        wait.until(visibilityOfElementLocated(nextButton)).click();
    }

    public void selectCountryForNumber() {
        wait.until(visibilityOfElementLocated(countryInput)).sendKeys("ru");
    }

    public void fillMonthInput() {
        wait.until(visibilityOfElementLocated(monthInput)).click();
        wait.until(visibilityOfElementLocated(month)).click();
    }

    public void fillDayInput() {
        wait.until(visibilityOfElementLocated(dayInput)).click();
//        wait.until(visibilityOfElementLocated(day)).click();
    }

    public void fillYearInput() {
        wait.until(visibilityOfElementLocated(yearInput)).click();
//        wait.until(visibilityOfElementLocated(year)).click();
    }

    public void fillGenderInput() {
        wait.until(visibilityOfElementLocated(genderInput)).click();
//        wait.until(visibilityOfElementLocated(gender)).click();
    }
}
