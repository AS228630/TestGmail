package ru.ifmo.testgmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SendLetterPage {
    private final WebDriverWait wait;

    private static final By messageBlockTitle = By.xpath("//android.widget.TextView[@text = 'Compose']");
    private static final By additionalFieldButton = By.id("com.google.android.gm:id/add_cc_bcc");
    private static final By ccLabel = By.id("com.google.android.gm:id/cc_heading");
    private static final By bccLabel = By.id("com.google.android.gm:id/bcc_heading");

    private static final By toInput = By.id("com.google.android.gm:id/to");
    private static final By ccInput = By.id("com.google.android.gm:id/cc");
    private static final By bccInput = By.id("com.google.android.gm:id/bcc");
    private static final By composeInput = By.id("com.google.android.gm:id/composearea_tap_trap_bottom");
    private static final By subjectInput = By.id("com.google.android.gm:id/subject");

    private static final By attachmentsButton = By.id("com.google.android.gm:id/add_attachment");
    private static final By attachFileLabel = By.xpath("//android.widget.TextView[@text = 'Attach file']");
    private static final By insertFromDriveLabel = By.xpath("//android.widget.TextView[@text = 'Insert from Drive']");
    private static final By sendMoneyLabel = By.xpath("//android.widget.TextView[@text = 'Send money']");
    private static final By requestMoneyLabel = By.xpath("//android.widget.TextView[@text = 'Request money']");
    private static final By cancelButton = By.id("com.google.android.gms:id/cancel_button");

    private static final By modesButton = By.xpath("//android.widget.ImageView[@content-desc = 'More options']");
    private static final By discardSendingLabel = By.xpath("//android.widget.TextView[@text = 'Discard']");
    private static final By confidentialModeLabel = By.xpath("//android.widget.TextView[@text = 'Confidential mode']");
    private static final By confidentialModeOn = By.xpath("//android.widget.Switch[@text = 'Confidential mode ON']");
    private static final By saveButton = By.xpath("//android.widget.TextView[@text = 'SAVE']");
    private static final By sendButton = By.id("com.google.android.gm:id/send");

    private static final By fromInput = By.id("com.google.android.gm:id/from_account_name");
    private static final By backButton = By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Navigate')]");

    public SendLetterPage(WebDriverWait wait) {
        this.wait = wait;
    }

    public void fillToField(String text) {
        wait.until(visibilityOfElementLocated(toInput)).sendKeys(text + "\n");
    }

    public void fillCcField(String text) {
        wait.until(visibilityOfElementLocated(ccInput)).sendKeys(text + "\n");
    }

    public void fillBccField(String text) {
        wait.until(visibilityOfElementLocated(bccInput)).sendKeys(text + "\n");
    }

    public void fillSubjectField(String text) {
        wait.until(visibilityOfElementLocated(subjectInput)).sendKeys(text + "\n");
    }

    public void fillComposeField(String text) {
        wait.until(visibilityOfElementLocated(composeInput)).sendKeys(text + "\n");
    }

    public WebElement getNewMessageBlock() {
        return wait.until(visibilityOfElementLocated(messageBlockTitle));
    }

    public void clickAdditionalFieldsButton() {
        wait.until(visibilityOfElementLocated(additionalFieldButton)).click();
    }

    public WebElement getCcLabel() {
        return wait.until(visibilityOfElementLocated(ccLabel));
    }

    public WebElement getBccLabel() {
        return wait.until(visibilityOfElementLocated(bccLabel));
    }

    public void clickAttachmentsButton() {
        wait.until(visibilityOfElementLocated(attachmentsButton)).click();
    }

    public void clickAttachFileLabel() {
        wait.until(visibilityOfElementLocated(attachFileLabel)).click();
    }

    public void clickInsertFromDriveLabel() {
        wait.until(visibilityOfElementLocated(insertFromDriveLabel)).click();
    }

    public void clickSendMoneyLabel() {
        wait.until(visibilityOfElementLocated(sendMoneyLabel)).click();
    }

    public void clickRequestMoneyLabel() {
        wait.until(visibilityOfElementLocated(requestMoneyLabel)).click();
    }

    public void clickCancelButton() {
        wait.until(visibilityOfElementLocated(cancelButton)).click();
    }

    public void clickModesButton() {
        wait.until(visibilityOfElementLocated(modesButton)).click();
    }

    public void clickDiscardLabelLabel() {
        wait.until(visibilityOfElementLocated(discardSendingLabel)).click();
    }

    public void clickConfidentialModeLabel() {
        wait.until(visibilityOfElementLocated(confidentialModeLabel)).click();
    }

    public void clickConfidentialModeSwitch() {
        wait.until(visibilityOfElementLocated(confidentialModeOn)).click();
    }

    public void clickSaveButton() {
        wait.until(visibilityOfElementLocated(saveButton)).click();
    }

    public void clickSendButton() {
        wait.until(visibilityOfElementLocated(sendButton)).click();
    }

    public By getByFromInput() {
        return fromInput;
    }

    public void clickBackButton() {
        wait.until(visibilityOfElementLocated(backButton)).click();
    }
}
