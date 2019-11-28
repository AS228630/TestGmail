package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MailPage {
    private final WebDriverWait wait;

    private static final By menuButton = By.xpath("//android.widget.ImageButton[@content-desc = 'Open navigation drawer']");
    private static final By primaryButton = By.xpath("//android.widget.TextView[@text = \"Primary\"]");
    private static final By socialButton = By.xpath("//android.widget.TextView[@text = \"Social\"]");
    private static final By promotionsButton = By.xpath("//android.widget.TextView[@text = \"Promotions\"]");
    private static final By sendButton = By.xpath("//android.widget.TextView[@text = \"Sent\"]");

    private static final By mainLabel = By.id("com.google.android.gm:id/conversation_list_folder_name");
    private static final By newMessageButton = By.id("com.google.android.gm:id/compose_button");
//    private static final By newMessageButton = By.xpath("//android.widget.ImageButton[@content-desc, \"Compose\"]");
//    private static final By newMessageButton = By.xpath("//android.widget.ImageButton[@content-desc, 'Compose']");

    private static final By sendNotification = By.id("com.google.android.gm:id/description_text");

    private final static By firstLetter = By.xpath("//android.view.View[contains(@content-desc, \"me\")]");
    private final static By unreadFirstLetter = By.xpath("//android.view.View[contains(@content-desc, 'Unread')]");
    private final static By starredLetters = By.xpath("//android.view.View[contains(@content-desc, 'Starred me')]");
    private final static By profileImage = By.id("com.google.android.gm:id/og_apd_internal_image_view");
    private final static By addAnotherAccountButton = By.xpath("//android.widget.TextView[@text = 'Add another account']");
    private final static By googleAccount = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout\n");

    public MailPage(WebDriverWait wait) {
        this.wait = wait;
    }

    public void clickMenuButton() {
        wait.until(visibilityOfElementLocated(menuButton)).click();
    }

    public WebElement getPrimaryButton() {
        return wait.until(visibilityOfElementLocated(primaryButton));
    }

    public WebElement getMainLabel() {
        return wait.until(visibilityOfElementLocated(mainLabel));
    }

    public void clickPrimaryButton() {
        wait.until(visibilityOfElementLocated(primaryButton)).click();
    }

    public void clickSocialButton() {
        wait.until(visibilityOfElementLocated(socialButton)).click();
    }

    public void clickPromotionsButton() {
        wait.until(visibilityOfElementLocated(promotionsButton)).click();
    }

    public void clickNewLetterButton() {
        wait.until(visibilityOfElementLocated(newMessageButton)).click();
    }

    public WebElement getSentNotification() {
        return wait.until(visibilityOfElementLocated(sendNotification));
    }

    public void clickSentLabel() {
        wait.until(visibilityOfElementLocated(sendButton)).click();
    }

    public void clickFirstLetter() {
        wait.until(visibilityOfElementLocated(firstLetter)).click();
    }

    public WebElement getUnreadFirstLetter() {
        return wait.until(visibilityOfElementLocated(unreadFirstLetter));
    }

    public WebElement getStarredLetters() {
        return wait.until(visibilityOfElementLocated(starredLetters));
    }

    public void clickProfileImage() {
        wait.until(visibilityOfElementLocated(profileImage)).click();
    }

    public void clickAddAnotherAccountButton() {
        wait.until(visibilityOfElementLocated(addAnotherAccountButton)).click();
    }

    public void clickGoogleAccountButton() {
        wait.until(visibilityOfElementLocated(googleAccount)).click();
    }
}
