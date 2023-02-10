package PageObjects;

import Utills.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FavoritesPage extends AndroidActions {
    AndroidDriver driver;
    public FavoritesPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Favorites']")
    private WebElement favoriteTab;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/icon_team_logo")
    private WebElement selectedTeam;


    public boolean verifyFavoritesPageDisplayed() {
        String focussed = favoriteTab.getAttribute("focusable");
        return focussed.equals("true");
    }

    public void clickSelectedTeam() {
        selectedTeam.click();
    }

}
