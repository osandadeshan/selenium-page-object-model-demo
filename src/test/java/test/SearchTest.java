package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.SearchResultPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static util.driver.DriverHolder.getDriver;

public class SearchTest extends BaseTest {

    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void searchBeforeMethod() {
        new LoginPage(getDriver()).login("osanda@mailinator.com","1qaz2wsx@");
        searchResultPage = new SearchResultPage(getDriver());
        new HomePage(getDriver()).searchItem("T-shirt");
    }

    @Test(description = "Verify that the search results are not empty for the keyword 'T-shirt'")
    public void testSearchResultCount() {
        int actualSearchResultCount = searchResultPage.getSearchResultCount();
        assertTrue(actualSearchResultCount > 0);
        assertEquals(actualSearchResultCount,1);
    }

    @Test(description = "Verify the search result name and price for the keyword 'T-shirt'")
    public void testSearchResult() {
        assertTrue(searchResultPage.getFirstSearchResultName().contains("Faded Short Sleeve T-shirts"));
        assertEquals(searchResultPage.getFirstSearchResultPrice(), "$16.51");
    }
}
