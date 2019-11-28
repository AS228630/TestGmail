package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SentPage {
    private final WebDriverWait wait;

    private final static By firstLetter = By.xpath("//android.view.View[contains(@content-desc, 'me')]");
    private final static By subjectLabel = By.id("com.google.android.gm:id/subject_and_folder_view");

    public SentPage(WebDriverWait wait) {
        this.wait = wait;
    }


    public void clickFirstLetter() {
        wait.until(visibilityOfElementLocated(firstLetter)).click();
    }

    public By getBySubject() {
        return subjectLabel;
    }

    public WebElement getComposeText(String text) {
        By composeText = By.xpath("//android.view.View[@text = '" + text + "']");
        return wait.until(visibilityOfElementLocated(composeText));
    }
}
