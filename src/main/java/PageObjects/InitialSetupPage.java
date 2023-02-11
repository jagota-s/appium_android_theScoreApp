package PageObjects;

import PageUtils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class InitialSetupPage extends AndroidActions {

    AndroidDriver driver;

    public InitialSetupPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement getGetStartedButton;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_primary")
    private WebElement league_continue_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/img_location")
    private WebElement location_popup;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_disallow")
    private WebElement deny_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/search_bar_placeholder")
    private WebElement searchBar;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/search_src_text")
    private WebElement searchText;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement continue_teamButton;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement done_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/button_positive")
    private WebElement download_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/dismiss_modal")
    private WebElement dismiss_button;


    public void clickGetStartedButton() {
        getGetStartedButton.click();
    }

    public boolean selectLeague(String leagueName) {
        WebElement league = driver.findElement(By.xpath("//*[@text='" + leagueName + "']"));
        if (league != null) {
            league.click();
            league_continue_button.click();
            return true;
        } else {
            return false;
        }
    }

    public void handleLocationPopup() {
        if (location_popup != null && location_popup.isDisplayed()) {
            deny_button.click();
        }
    }

    public boolean selectTeam(String teamName) throws InterruptedException {
        searchBar.click();
        searchText.sendKeys(teamName);
        Thread.sleep(2000);
        WebElement team = driver.findElement(By.xpath("//*[contains(@text,'" + teamName + "')]"));
        if (team != null) {
            team.click(); // todo click not working wtf
            continue_teamButton.click();
            return true;
        } else {
            return false;
        }
    }

    public void clickDoneButton() {
        done_button.click();
        handleAdModalPopUp();
    }

    private void handleAdModalPopUp() {
        if (download_button != null && download_button.isDisplayed()) {
            dismiss_button.click();
        }
    }


}
