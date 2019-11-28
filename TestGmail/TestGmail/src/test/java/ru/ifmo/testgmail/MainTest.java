package ru.ifmo.testgmail;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.testgmail.page.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

;

public class MainTest {
    private static AndroidDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Custom Phone_1");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "com.google.android.gm.PublicGmailActivity");
        capabilities.setCapability("adbExecTimeout", "1000000");//adbExecTimeout

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 30);

        firstSteps();
    }

    private static void firstSteps() {
        wait.until(visibilityOfElementLocated(By.id("com.google.android.gm:id/welcome_tour_got_it"))).click();
        wait.until(visibilityOfElementLocated(By.id("com.google.android.gm:id/account_display_name"))).click();
        wait.until(visibilityOfElementLocated(By.id("com.google.android.gm:id/action_done"))).click();
        wait.until(visibilityOfElementLocated(By.id("com.google.android.gm:id/gm_dismiss_button"))).click();
        wait.until(visibilityOfElementLocated(By.id("com.google.android.gm:id/gm_dismiss_button"))).click();
    }

    @Test
    public void switchingFoldersTest() {
        MailPage mailPage = new MailPage(wait);
//      //  mailPage.clickMenuButton();

        assertNotNull(mailPage.getPrimaryButton());

        mailPage.clickSocialButton();
        assertEquals(mailPage.getMainLabel().getText(), "SOCIAL");

        mailPage.clickMenuButton();
        mailPage.clickPrimaryButton();
        assertEquals(mailPage.getMainLabel().getText(), "PRIMARY");

        mailPage.clickMenuButton();
        mailPage.clickPromotionsButton();
        assertEquals(mailPage.getMainLabel().getText(), "PROMOTIONS");
    }

    @Test
    public void openNewMessageBlockTest() {
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);
        assertEquals(sendLetterPage.getNewMessageBlock().getText(), "Compose");

        sendLetterPage.clickAdditionalFieldsButton();
        assertEquals(sendLetterPage.getCcLabel().getText(), "Cc");
        assertEquals(sendLetterPage.getBccLabel().getText(), "Bcc");

        sendLetterPage.clickBackButton();
    }

    @Test
    public void enterAllFieldsTest() {
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);
        sendLetterPage.clickAdditionalFieldsButton();

        sendLetterPage.fillToField("Asibullah43@yahoo.com");
        sendLetterPage.fillCcField("da1");
        sendLetterPage.fillBccField("da2");
        sendLetterPage.fillSubjectField("exampleSubject");
        sendLetterPage.fillComposeField("exampleText");

        sendLetterPage.clickBackButton();
    }

    @Test
    public void attachmentsTest() {
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);

        sendLetterPage.clickAttachmentsButton();
        sendLetterPage.clickAttachFileLabel();
        assertEquals(new RecentFilePage(wait).getMainLabel().getText(), "Recent");
        driver.navigate().back();

        sendLetterPage.clickAttachmentsButton();
        sendLetterPage.clickInsertFromDriveLabel();
        GoogleDrivePage googleDrivePage = new GoogleDrivePage(wait);
        assertEquals(googleDrivePage.getMainLabel().getText(), "My Drive");
        googleDrivePage.clickCancelButton();

        sendLetterPage.clickAttachmentsButton();
        sendLetterPage.clickSendMoneyLabel();
        sendLetterPage.clickCancelButton(); //

        sendLetterPage.clickAttachmentsButton();
        sendLetterPage.clickRequestMoneyLabel();
        sendLetterPage.clickCancelButton();

        driver.navigate().back();
    }

    @Test
    public void modesTest() {
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);

        sendLetterPage.clickModesButton();
        sendLetterPage.clickDiscardLabelLabel();
        assertEquals(mailPage.getMainLabel().getText(), "PRIMARY");
    }

    @Test
    public void confidentialModeTest() {
        MailPage mailPage = new MailPage(wait);
        mailPage.clickNewLetterButton(); //?

        SendLetterPage sendLetterPage = new SendLetterPage(wait);
        sendLetterPage.clickModesButton();
        sendLetterPage.clickConfidentialModeLabel();
        sendLetterPage.clickConfidentialModeSwitch();
        sendLetterPage.clickSaveButton();
        assertEquals(sendLetterPage.getNewMessageBlock().getText(), "Compose");

        driver.navigate().back();
    }

    @Test
    public void sendLetterToSomeoneTest() {
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);

        String toField = "Asibullah43@yahoo.com";
        String subjectField = "exampleSubject";
        String composeField = "exampleText";

        sendLetterPage.fillToField(toField);
        sendLetterPage.fillSubjectField(subjectField);
        sendLetterPage.fillComposeField(composeField);
        sendLetterPage.clickSendButton();

        String notificationText = mailPage.getSentNotification().getText();
        assert notificationText.equals("Sent") || notificationText.equals("Sending…");

//        mailPage.clickMenuButton();
//        wait.until(visibilityOfElementLocated(By.xpath ("//android.widget.LinearLayout[@bounds='[0,1094][840,1220]']"))).click();
//        mailPage.clickSentLabel();

//        SentPage sentPage = new SentPage(wait);
//        sentPage.clickFirstLetter();

//        wait.until(textToBe(sentPage.getBySubject(), subjectField + " Add label "));
//        assertEquals(sentPage.getComposeText(composeField).getText(), composeField);

//        driver.navigate().back();
    }

    @Test
    public void sendLetterToMyselfTest() { //!
        MailPage mailPage = new MailPage(wait);
        //mailPage.clickNewLetterButton();

        SendLetterPage sendLetterPage = new SendLetterPage(wait);

        String toField = "asibullahitmo@gmail.com";
        String subjectField = "exampleSubject";
        String composeField = "exampleText";

        sendLetterPage.fillToField(toField);
        sendLetterPage.fillSubjectField(subjectField);
        sendLetterPage.fillComposeField(composeField);
        sendLetterPage.clickSendButton();

        mailPage.clickMenuButton();
        mailPage.clickPrimaryButton();
        mailPage.clickFirstLetter(); //click

        ViewLetterPage viewLetterPage = new ViewLetterPage(wait);
        wait.until(textToBe(viewLetterPage.getBySubject(), subjectField + " . Inbox "));
        assertEquals(viewLetterPage.getToField().getText(), "to me");

        driver.navigate().back();
    }

    @Test
    public void replyLetterTest() {
        MailPage mailPage = new MailPage(wait);
        mailPage.clickFirstLetter();

        ViewLetterPage viewLetterPage = new ViewLetterPage(wait);
        viewLetterPage.clickReplyButton();
        try {
            viewLetterPage.clickReplyButton();
        } catch (TimeoutException exception) {
        }

        SendLetterPage sendLetterPage = new SendLetterPage(wait);
        wait.until(textToBe(sendLetterPage.getByFromInput(), "asibullahitmo@gmail.com"));
        sendLetterPage.clickSendButton();

        viewLetterPage.clickBackButton();
        assertNotNull(mailPage.getUnreadFirstLetter());

        mailPage.clickFirstLetter();
    }

    @Test
    public void forwardLetterTest() {
        MailPage mailPage = new MailPage(wait);
        mailPage.clickFirstLetter();

        ViewLetterPage viewLetterPage = new ViewLetterPage(wait);
        viewLetterPage.clickForwardButton();
        try {
            viewLetterPage.clickForwardButton();
        } catch (TimeoutException exception) {
        }

        SendLetterPage sendLetterPage = new SendLetterPage(wait);
        sendLetterPage.fillToField("asibullahitmo@gmail.com");
        wait.until(textToBe(sendLetterPage.getByFromInput(), "asibullahitmo@gmail.com"));
        sendLetterPage.clickSendButton();

        viewLetterPage.clickBackButton();
        assertNotNull(mailPage.getUnreadFirstLetter());

        mailPage.clickFirstLetter();

        driver.navigate().back();
    }

    @Test
    public void notReadLetterTest() {
        MailPage mailPage = new MailPage(wait);
//        mailPage.clickFirstLetter();

        ViewLetterPage viewLetterPage = new ViewLetterPage(wait);
        try {
            viewLetterPage.clickReplyButton();
            driver.navigate().back();
        } catch (TimeoutException Testd) {
        }
        viewLetterPage.clickUnreadButton();

        assertNotNull(mailPage.getUnreadFirstLetter());
    }

    @Test
    public void setAndUnsetImportantLetterTest() {
        MailPage mailPage = new MailPage(wait);
        mailPage.clickFirstLetter();
        ViewLetterPage viewLetterPage = new ViewLetterPage(wait);
        viewLetterPage.clickImportantButton();

        driver.navigate().back();
        assertNotNull(mailPage.getStarredLetters());

        mailPage.clickFirstLetter();
        viewLetterPage = new ViewLetterPage(wait);
        viewLetterPage.clickImportantButton();

        driver.navigate().back();
        boolean check = false;
        try {
            mailPage.getStarredLetters();
        } catch (TimeoutException e) {
            check = true;
        }

        assert check;
    }

    @Test
    public void registrationTest() {
        MailPage mailPage = new MailPage(wait);
        mailPage.clickAddAnotherAccountButton();
        mailPage.clickGoogleAccountButton();

        SignInPage signInPage = new SignInPage(wait);
        pause(15000L);
        signInPage.clickCreateAccountButton();//
        signInPage.fillFirstNameInputByRandom();
        signInPage.fillLastNameInputByRandom();
        signInPage.clickNextButton();

//        signInPage.fillMonthInput();

        signInPage.fillTelephoneNumber();

        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }

    @Test
    public void labelTest() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        WebElement workspace = driver.findElementById("com.android.launcher3:id/workspace");

        AndroidTouchAction touch = new AndroidTouchAction(driver);
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(workspace))).perform();
        pause(100L);
        WebElement wdBtn = driver.findElementById("com.android.launcher3:id/widget_button");

        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(wdBtn))).perform();

        Dimension size = driver.manage().window().getSize();

        double anchorPercentage = 0.5;

        int anchor = (int) (size.width * anchorPercentage);
        double startPercentage = 0.95;
        int startPoint = (int) (size.height * startPercentage);
        double endPercentage = 0.1;
        int endPoint = (int) (size.height * endPercentage);

        int counter = 0;
        do {
            touch
                    .press(point(anchor, startPoint))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();

            pause(500L);
            counter++;
        } while (counter != 2);

        List<WebElement> gmailWd = driver.findElements(By.xpath("//android.widget.TextView[@content-desc='Gmail']/..//com.android.launcher3.widget.WidgetCell"));

        assertEquals(gmailWd.size(), 2);

        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(gmailWd.get(1))))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(size.width / 2, size.height / 2))
                .waitAction(waitOptions(ofMillis(500)))
                .release()
                .perform();

        pause(1000L);

        assertEquals(driver.getCurrentPackage(), "com.google.android.gm");
        assert (driver.currentActivity().contains("FolderSelectionActivityGmail"));

        WebElement folder = wait.until(visibilityOfElementLocated(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout")));

        folder.click();

        WebElement doneBtn = wait.until(presenceOfElementLocated(By.id("com.google.android.gm:id/done")));

        doneBtn.click();

        WebElement widget = wait.until(presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='Primary']")));

        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(widget)))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(size.width / 2, (int) (size.height * 0.07)))
                .waitAction(waitOptions(ofMillis(500)))
                .release()
                .perform();

        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Gmail\"]"))).click();
    }

    @Test
    public void widgetTest() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        WebElement workspace = driver.findElementById("com.android.launcher3:id/workspace");

        AndroidTouchAction touch = new AndroidTouchAction(driver);
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(workspace))).perform();
        pause(100L);
        WebElement wdBtn = driver.findElementById("com.android.launcher3:id/widget_button");

        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(wdBtn))).perform();

        Dimension size = driver.manage().window().getSize();

        double anchorPercentage = 0.5;

        int anchor = (int) (size.width * anchorPercentage);
        double startPercentage = 0.95;
        int startPoint = (int) (size.height * startPercentage);
        double endPercentage = 0.1;
        int endPoint = (int) (size.height * endPercentage);

        int counter = 0;
        do {
            touch
                    .press(point(anchor, startPoint))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();

            pause(500L);
            counter++;
        } while (counter != 2);

        List<WebElement> gmailWd = driver.findElements(By.xpath("//android.widget.TextView[@content-desc='Gmail']/..//com.android.launcher3.widget.WidgetCell"));

        assertEquals(gmailWd.size(), 2);

        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(gmailWd.get(0))))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(size.width / 2, size.height / 2))
                .waitAction(waitOptions(ofMillis(500)))
                .release()
                .perform();

        pause(1000L);

        assertEquals(driver.getCurrentPackage(), "com.google.android.gm");

        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout"))).click();

        pause(1000L);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        WebElement widget = wait.until(presenceOfElementLocated(By.xpath("//com.android.launcher3.LauncherAppWidgetHostView[@content-desc='Gmail']/android.widget.LinearLayout"))); //

        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(widget)))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(size.width / 2, (int) (size.height * 0.07)))
                .waitAction(waitOptions(ofMillis(500)))
                .release()
                .perform();

        wait.until(visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Gmail\"]"))).click();
    }

    private void pause(Long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
