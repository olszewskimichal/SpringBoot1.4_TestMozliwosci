package com.register.example.selenium;

import com.register.example.selenium.configuration.ScreenshotTestRule;
import com.register.example.selenium.configuration.SeleniumTestBase;
import com.register.example.selenium.pageObjects.ResetPasswordPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class ResetPasswordSeleniumTest extends SeleniumTestBase {

    public static WebDriver driver;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

    @BeforeClass
    public static void setUp() {
        driver = browserConfiguration.firefox();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }


    @Test
    public void shouldResetPasswordWithCorrectData() {
        driver.get("http://localhost:" + port + "/resetPassword?token=testowyToken");
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.typePassword("zaq1@WSX");
        resetPasswordPage.typePasswordConfirm("zaq1@WSX");
        resetPasswordPage.clickOnSendButton();
        System.out.println(driver.getPageSource() + "\n////////////////////////////////////");
        System.out.println(driver.getTitle() + "\n////////////////////////////////////");
        assertThat(driver.getPageSource()).contains("Twoje hasło zostało zmienione");
        assertThat(driver.getTitle()).isEqualTo("Strona logowania");
    }


    @Test
    public void shouldResetPasswordFailedWithUsedToken() {
        driver.get("http://localhost:" + port + "/resetPassword?token=testowyToken2");
        System.out.println(driver.getPageSource() + "\n////////////////////////////////////");
        System.out.println(driver.getTitle() + "\n////////////////////////////////////");
        assertThat(driver.getPageSource()).contains("Podany token juz został wykorzystany");
        assertThat(driver.getTitle()).isEqualTo("Strona logowania");
    }

    @Test
    public void shouldResetPasswordFailedWithNotExistingToken() {
        driver.get("http://localhost:" + port + "/resetPassword?token=dupa");
        System.out.println(driver.getPageSource() + "\n////////////////////////////////////");
        System.out.println(driver.getTitle() + "\n////////////////////////////////////");
        assertThat(driver.getPageSource()).contains("Błedny link resetujacy hasło");
        assertThat(driver.getTitle()).isEqualTo("Strona logowania");
    }

}
