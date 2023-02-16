import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AnnaDolmatovaTest {

    @Test
    public void testH2TagTest_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//input[@placeholder='Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(3000);

        WebElement parisFranceChoiceInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFranceChoiceInDropDownMenu.click();

        Thread.sleep(3000);

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@class='current-container mobile-padding']/div/h2")
        );

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }

    @Test
    public void testGuideUrlAndHeader() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);

        Thread.sleep(5000);

        WebElement guideElementInMenu =  driver.findElement(
                By.xpath("//a[@href='/guide']"));
        guideElementInMenu.click();

        Thread.sleep(2000);

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultUrl, expectedResultUrl);

        driver.quit();
    }

    @Test
    public void testChangeMetricOfTheTemperature() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        driver.get(url);

        Thread.sleep(5000);

        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']"));

        menuImperial.click();

        Thread.sleep(2000);

        WebElement temperatureF = driver.findElement(
                By.xpath("//span[@class='heading']"));

        Assert.assertTrue(temperatureF.getText().contains("°F"));

        driver.quit();
    }

    @Test
    public void testCookiesPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedDescription = "We use cookies which are essential for " +
                "the site to work. We also use non-essential cookies to help us " +
                "improve our services. Any data collected is anonymised. " +
                "You can allow all cookies or manage them individually.";
        String button1Text = "Allow all";
        String button2Text = "Manage cookies";

        driver.get(url);

        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class='stick-footer-panel']")).isDisplayed());

        WebElement cookiesPanelDescription = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']"));
        String actualCookiesPanelDescription = cookiesPanelDescription.getText();

        Assert.assertEquals(actualCookiesPanelDescription, expectedDescription);

        Assert.assertEquals(driver.findElements(
                By.cssSelector(".stick-footer-panel__link")).size(), 2);

        Assert.assertTrue(driver.findElement(
                By.xpath("//button[@class='stick-footer-panel__link']")).getText().contains(button1Text));

        Assert.assertTrue(driver.findElement(
                By.xpath("//a[@class='stick-footer-panel__link']")).getText().contains(button2Text));

        driver.quit();
    }

    @Test
    public void testSupportDropDownMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String button1Text = "FAQ";
        String button2Text = "How to start";
        String button3Text = "Ask a question";

        driver.get(url);

        Thread.sleep(5000);

        driver.manage().window().maximize();

        driver.findElement(new By.ById("support-dropdown")).click();

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(
                By.xpath("//ul[@id='support-dropdown-menu']/li")).size(), 3);

        Assert.assertTrue(driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[1]")).getText().contains(button1Text));

        Assert.assertTrue(driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[2]")).getText().contains(button2Text));

        Assert.assertTrue(driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]")).getText().contains(button3Text));

        driver.quit();
    }

    @Test
    public void testAskAQuestionTabFail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "google1@gmail.com";
        String message = "Hello";

        driver.get(url);

        Thread.sleep(5000);

        driver.manage().window().maximize();

        driver.findElement(new By.ById("support-dropdown")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[3]")).click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        driver.manage().window().maximize();

        Thread.sleep(2000);

        driver.findElement(new By.ById("question_form_email")).sendKeys(email);
        driver.findElement(new By.ById("question_form_subject")).click();
        driver.findElement(By.xpath("//option[@value='Sales']")).click();
        driver.findElement(new By.ById("question_form_message")).sendKeys(message);
        driver.findElement(By.xpath("//input[@class='btn btn-default']")).click();

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class='help-block']")).getText().contains(expectedResult));


        driver.quit();
    }

    @Test
    public void testConfirmErrorInEmailField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";
        String message = "Hello";

        driver.get(url);

        Thread.sleep(5000);

        driver.manage().window().maximize();

        driver.findElement(new By.ById("support-dropdown")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']/li[3]")).click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        driver.manage().window().maximize();

        Thread.sleep(2000);

        driver.findElement(new By.ById("question_form_subject")).click();
        driver.findElement(By.xpath("//option[@value='Sales']")).click();
        driver.findElement(new By.ById("question_form_message")).sendKeys(message);

        WebElement iframeCaptchaWindow = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
        driver.switchTo().frame(iframeCaptchaWindow);

        WebElement checkboxCaptcha =
                driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']"));
        checkboxCaptcha.click();

        driver.switchTo().window(tabs2.get(1));

        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@class='btn btn-default']")).click();

        Assert.assertTrue(driver.findElement(
                By.xpath("//span[@class='help-block']")).getText().contains(expectedResult));


        driver.quit();
    }

    @Test
    public void testReloadPageSuccessfully() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(5000);

        WebElement logo = driver.findElement(
                By.xpath("//ul[@id='first-level-nav']/li/a/img"));
        logo.click();

        Thread.sleep(5000);

        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, url);

        driver.quit();
    }

    @Test
    public void testSearchRome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";

        driver.get(url);

        Thread.sleep(5000);

        WebElement navigationField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/form/input[1]"));
        navigationField.sendKeys(cityName);
        navigationField.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

//        String actualCurrentUrl = driver.getCurrentUrl();

        Assert.assertTrue(driver.getCurrentUrl().contains("find"));
        Assert.assertTrue(driver.getCurrentUrl().contains("Rome"));
        Assert.assertTrue(driver.findElement(
                By.xpath("//input[@id='search_str']")).getAttribute("value").contains("Rome"));

        driver.quit();
    }

    @Test
    public void testApiPageOrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annad\\IdeaProjects\\WebdriverChrome\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);

        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@id='desktop-menu']/ul/li[2]/a")).click();

        Thread.sleep(2);

        Assert.assertEquals(driver.findElements(
                By.xpath("//a[contains(@class,'orange')]")).size(), 30);

        driver.quit();
    }
}
