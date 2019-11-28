package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ViewLetterPage {
    private final WebDriverWait wait;

    private final static By subjectLabel = By.id("com.google.android.gm:id/subject_and_folder_view");
    private final static By toMeLabel = By.id("com.google.android.gm:id/recipient_summary");
    private final static By replyButton = By.id("com.google.android.gm:id/reply_button_text");
    private final static By backButton = By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate')]");
    private final static By importantButton = By.id("com.google.android.gm:id/conversation_header_star");
    private final static By unreadButton = By.id("com.google.android.gm:id/inside_conversation_unread");
//    private final static By unreadButton = By.xpath("//android.widget.TextView[@content-desc, \"Mark unread\"]");
    private final static By forwardButton = By.id("com.google.android.gm:id/forward_button");

    public ViewLetterPage(WebDriverWait wait) {
        this.wait = wait;
    }

    public By getBySubject() {
        return subjectLabel;
    }

    public WebElement getToField() {
        return wait.until(visibilityOfElementLocated(toMeLabel));
    }

    public void clickReplyButton() {
        wait.until(visibilityOfElementLocated(replyButton)).click();
    }

    public void clickBackButton() {
        wait.until(visibilityOfElementLocated(backButton)).click();
    }

    public void clickImportantButton() {
        wait.until(visibilityOfElementLocated(importantButton)).click();
    }

    public void clickUnreadButton() {
        wait.until(visibilityOfElementLocated(unreadButton)).click();
    }

    public void clickForwardButton() {
        wait.until(visibilityOfElementLocated(forwardButton)).click();
    }
}
