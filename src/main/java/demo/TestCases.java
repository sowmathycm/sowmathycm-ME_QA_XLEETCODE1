package demo;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    private WebDriverWait wait;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        String currentURL = driver.getCurrentUrl();
        String expectedTitle = "leetcode";
        if (currentURL.contains(expectedTitle)) {
            System.out.println("The URL contains the expected title" + " " + expectedTitle);
        } else {
            System.out.println("The URL does not contain the expected title" + " " + expectedTitle);
        }
        System.out.println("end Test case: testCase01");

    }

    /**
     * @throws InterruptedException
     * 
     */
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get("https://leetcode.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement questions = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/problemset/']")));
        questions.click();
        String currentURL = driver.getCurrentUrl();
        String expectedTitle = "problemset";
        if (currentURL.contains(expectedTitle)) {
            System.out.println("The URL contains the expected title" + " " + expectedTitle);
        } else {
            System.out.println("The URL does not contain the expected title" + " " + expectedTitle);
        }
        Thread.sleep(5000);
        List<WebElement> questionTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(
                        "//div[@class='inline-block min-w-full']//div[@role='rowgroup']//div[@class='mx-2 flex items-center py-[11px]']//a[@class='h-5 hover:text-blue-s dark:hover:text-dark-blue-s']")));

        for (int i = 1; i <= 5 && i < questionTitles.size(); i++) {
            String questionTitle = questionTitles.get(i).getText();
            System.out.println("Question " + i + ": " + questionTitle);
        }

        boolean TwoSum = false;

        for (WebElement questionTitleElement : questionTitles) {
            String questionTitle = questionTitleElement.getText();
            if (questionTitle.contains("Two Sum")) {
                TwoSum = true;
                System.out.println("Question Title 'Two Sum' found");
                WebElement twosum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//a[contains(@class, 'h-5 hover:text-blue-s dark:hover:text-dark-blue-s') and contains(@href, '/problems/two-sum')]")));
                twosum.click();
                break;
            }
        }

        if (TwoSum) {

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("two-sum")) {
                System.out.println("URL contains 'two-sum'");
            } else {
                System.out.println("URL does not contain 'two-sum'");
            }
        } else {
            System.out.println("Title 'Two Sum' not found");
        }

        System.out.println("end Test case: testCase02");

    }

    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        driver.get("https://leetcode.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement questions = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/problemset/']")));
        questions.click();
        WebElement twosum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//a[contains(@class, 'h-5 hover:text-blue-s dark:hover:text-dark-blue-s') and contains(@href, '/problems/two-sum')]\n"
                        + //
                        "")));
        twosum.click();
        String currentURL = driver.getCurrentUrl();
        String expectedTitle = "two-sum";
        if (currentURL.contains(expectedTitle)) {
            System.out.println("The URL contains the expected title" + " " + expectedTitle);
        } else {
            System.out.println("The URL does not contain the expected title" + " " + expectedTitle);
        }
        System.out.println("end Test case: testCase03");

    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        driver.get("https://leetcode.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement questions = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/problemset/']")));
        questions.click();
        WebElement twosum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//a[contains(@class, 'h-5 hover:text-blue-s dark:hover:text-dark-blue-s') and contains(@href, '/problems/two-sum')]")));
        twosum.click();
        WebElement enable = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Enable Dynamic Layout']")));
        enable.click();
        WebElement skip = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Skip tour']")));
        skip.click();
        WebElement submission = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='normal absolute left-0 top-0 whitespace-nowrap font-normal'][contains(text(), 'Submissions')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submission);
        submission.click();
        Thread.sleep(2000);
        WebElement register = driver.findElement(By.xpath("//a[contains(text(), 'Register or Sign In')]"));
        String text = register.getText();
        if (text.equals("Register or Sign In")) {
            System.out.println("The message \"Register or Sign In\" is displayed");
        } else {
            System.out.println("The message \"Register or Sign In\" is not displayed");
        }

        System.out.println("end Test case: testCase04");

    }
}
