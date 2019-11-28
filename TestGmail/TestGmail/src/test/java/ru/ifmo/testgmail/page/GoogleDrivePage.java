package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GoogleDrivePage {
    private final WebDriverWait wait;

    public GoogleDrivePage(WebDriverWait wait) {
        this.wait = wait;
    }

    private static final By mainLabel = By.id("com.google.android.gms:id/action_bar_folder");
    private static final By cancelButton = By.id("com.google.android.gms:id/drive_button_bar_button_left");

    public WebElement getMainLabel() {
        return wait.until(visibilityOfElementLocated(mainLabel));
    }

    public void clickCancelButton() {
        wait.until(visibilityOfElementLocated(cancelButton)).click();
    }
}
