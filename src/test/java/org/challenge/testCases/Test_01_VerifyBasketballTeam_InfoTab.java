package org.challenge.testCases;

import org.challenge.pageObjects.FavoritesPage;
import org.challenge.pageObjects.InitialSetupPage;
import org.challenge.pageObjects.TeamPage;
import org.challenge.testUtils.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class Test_01_VerifyBasketballTeam_InfoTab extends BaseTest {
    @Test(dataProvider = "getData")
    public void Test_01_VerifyBasketballTeam_InfoTab(HashMap<String, String> input) throws MalformedURLException, InterruptedException {
        InitialSetupPage initialSetupPage = new InitialSetupPage(getDriver());
        initialSetupPage.clickGetStartedButton();
        initialSetupPage.selectLeague(input.get("leagueName"));
        initialSetupPage.handleLocationPopup();
        initialSetupPage.selectTeam(input.get("teamName"));
        initialSetupPage.clickDoneButton();

        FavoritesPage favoritesPage = new FavoritesPage(getDriver());
        favoritesPage.verifyFavoritesPageDisplayed();
        favoritesPage.clickSelectedTeam();

        TeamPage teamPage = new TeamPage(getDriver());
        teamPage.validateSelectedTeamName(input.get("teamName"));
        teamPage.selectInfoTab();
        teamPage.validateInfoTabDetails(input.get("teamLocation"), input.get("teamArena"));
        teamPage.goBack();

        favoritesPage.verifyFavoritesPageDisplayed();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(this.getClass().getSimpleName());
        return new Object[][]{{data.get(0)}};
    }

}
