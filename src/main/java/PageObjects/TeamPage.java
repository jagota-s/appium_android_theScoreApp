package PageObjects;

import PageUtils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TeamPage extends AndroidActions {

    AndroidDriver driver;
    public TeamPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.fivemobile.thescore:id/team_name")
    private WebElement teamName;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Info']")
    private WebElement infoTab;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/value")
    private List<WebElement> infoTextValues;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Schedule']")
    private WebElement scheduleTab;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@content-desc,'Player Stats')]")
    private WebElement playerStatsTab;



    public String getSelectedTeamName() {
        return teamName.getText();
    }

    public void selectInfoTab() {
        this.swipe(scheduleTab, "left");
        this.swipe(playerStatsTab, "left");
        infoTab.click();
    }
    public List<WebElement> locationInfo() {
        return infoTextValues;
    }

    public void goBack() {
        this.pressBack(AndroidKey.BACK);
    }

}
