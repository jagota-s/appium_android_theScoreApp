package org.challenge.testCases;

import com.aventstack.extentreports.Status;
import org.challenge.pageObjects.FavoritesPage;
import org.challenge.pageObjects.WelcomePage;
import org.challenge.pageObjects.TeamPage;
import org.challenge.testUtils.BaseTest;
import org.challenge.testUtils.ExtentReporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class Test_01_VerifyBasketballTeam_InfoTab extends BaseTest {
    public static final String TEAM_NAME = "teamName";
    public static final String LEAGUE_NAME = "leagueName";
    public static final String TEAM_LOCATION = "teamLocation";
    public static final String TEAM_ARENA = "teamArena";

    @Test(dataProvider = "getData")
    public void Test_01_VerifyBasketballTeam_InfoTab(HashMap<String, String> inputData) {
        WelcomePage welcomePage = new WelcomePage(getDriver());
        welcomePage.clickGetStartedButton();
        welcomePage.handleCcpaModal();

        welcomePage.selectLeague(inputData.get(LEAGUE_NAME));
        welcomePage.handleLocationPopup();
        welcomePage.selectTeam(inputData.get(TEAM_NAME));
        welcomePage.clickDoneButton();

        FavoritesPage favoritesPage = new FavoritesPage(getDriver());
        favoritesPage.verifyFavoritesPageDisplayed();
        favoritesPage.clickSelectedTeam();

        TeamPage teamPage = new TeamPage(getDriver());
        teamPage.validateSelectedTeamName(inputData.get(TEAM_NAME));
        teamPage.selectInfoTab();
        teamPage.validateInfoTabDetails(inputData.get(TEAM_LOCATION), inputData.get(TEAM_ARENA));
        teamPage.goBack();

        favoritesPage.verifyFavoritesPageDisplayed();
    }

    @DataProvider
    public Object[][] getData() {
        try {
            List<HashMap<String, String>> data = getJsonData(this.getClass().getSimpleName());
            return new Object[][]{{data.get(0)}};
        } catch (IOException e) {
            ExtentReporter.getTest().log(Status.ERROR, "Unable to read the data from test data JSON file. " + e.getMessage());
        }
        return null;
    }
}
