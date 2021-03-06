package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

     protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_LINE,
        SEARCH_CANCEL_BUTTON,
        SEARCH_CLEAR_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_RESULT_TITTLE,
        SEARCH_EMPTY_RESULTS_ELEMENT;


    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES_METHODS*/
    private static String getResultSearchElementBySubstring(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /*TEMPLATES_METHODS*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void clearSearchLine()
    {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClear(
                    SEARCH_LINE,
                    "Cannot find search field",
                    5
            );
        } else {
            this.waitForElementAndClick(SEARCH_CLEAR_BUTTON, "Cannot find and click search clear button", 5);
        }
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElementBySubstring(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring");
    }

    public void waitForSearchResultNotPresent(String substring)
    {
        String search_result_xpath = getResultSearchElementBySubstring(substring);
        this.waitForElementNotPresent(search_result_xpath, "Found search result with substring that should not be present", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElementBySubstring(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring", 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULTS_ELEMENT, "Cannot find Empty Result Element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results ");
    }

}

