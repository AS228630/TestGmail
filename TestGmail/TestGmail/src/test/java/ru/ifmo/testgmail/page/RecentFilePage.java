package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RecentFilePage {
    private final WebDriverWait wait;

    public RecentFilePage(WebDriverWait wait) {
        this.wait = wait;
    }

    private static final By mainLabel = By.xpath("//android.widget.TextView[@text = 'Recent']");

    public WebElement getMainLabel() {
        return wait.until(visibilityOfElementLocated(mainLabel));
    }
}
