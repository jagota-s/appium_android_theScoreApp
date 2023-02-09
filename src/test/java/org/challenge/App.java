package org.challenge;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class App extends BaseTest
{
    @Test
    public void appium() throws MalformedURLException, InterruptedException {

        System.out.println( "Hello World!" );
        //Thread.sleep(5000);
        WebElement getStartedButton = driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text"));
        getStartedButton.click();
       // Thread.sleep(5000);

        WebElement league = driver.findElement(By.xpath("//*[@text='NBA Basketball']"));
        league.click();
       // Thread.sleep(10000);

        WebElement continue_button = driver.findElement(By.id("com.fivemobile.thescore:id/btn_primary"));
        continue_button.click();

        // click location deny on popup
        WebElement locationPopUp = driver.findElement(By.id("com.fivemobile.thescore:id/img_location"));
        if (locationPopUp != null && locationPopUp.isDisplayed()) {
            driver.findElement(By.id("com.fivemobile.thescore:id/btn_disallow")).click();
        }

        // search the team
        WebElement searchBar = driver.findElement(By.id("com.fivemobile.thescore:id/search_bar_placeholder"));
        searchBar.click();

        WebElement searchText = driver.findElement(By.id("com.fivemobile.thescore:id/search_src_text"));
        searchText.sendKeys("Raptors");

        WebElement team = driver.findElement(By.xpath("//*[contains(@text,'Toronto Raptors')]"));
        team.click();


        WebElement continue_buttonteam = driver.findElement(By.id("com.fivemobile.thescore:id/action_button_text"));
        continue_buttonteam.click();

       WebElement done_button = driver.findElement(By.id("com.fivemobile.thescore:id/btn_primary"));
       done_button.click();


        WebElement download_button = driver.findElement(By.id("com.fivemobile.thescore:id/button_positive"));
        if (download_button != null && download_button.isDisplayed()) {
            driver.findElement(By.id("com.fivemobile.thescore:id/dismiss_modal")).click();
        }

        WebElement selectedTeam = driver.findElement(By.id("com.fivemobile.thescore:id/icon_team_logo"));
        selectedTeam.click();

        String teamName = driver.findElement(By.id("com.fivemobile.thescore:id/team_name")).getText();
        Assert.assertEquals(teamName, "Toronto Raptors");


        // WebElement infoTab = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc='Info']"));
        //WebElement infoTab = driver.findElement(AppiumBy.accessibilityId("Info"));
        //infoTab.click();

        // swipe to the info tab
        // //android.widget.LinearLayout[@content-desc="Info"]
        // accessibility id = Info
        // click on the above info tab

        List<WebElement> infoTextValues = driver.findElements(By.id("com.fivemobile.thescore:id/value"));
        String location = infoTextValues.get(0).getText();
        String arena = infoTextValues.get(1).getText();

        //id = com.fivemobile.thescore:id/value
        // return the location and arena in this order
        // assert the values

        //press back
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        // check if you are back on the favorites tab or not
        WebElement favoriteTab = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc='Favorites']"));
        String focussed = favoriteTab.getAttribute("focusable");
        Assert.assertEquals(focussed, "true");
        // //android.widget.FrameLayout[@content-desc="Favorites"]
        // enabled = true
        // focusable = true (only for selected value)








        ///widget.RecyclerView/android.view.ViewGroup[5]
    }
}
