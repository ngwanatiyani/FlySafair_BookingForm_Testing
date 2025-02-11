package selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlySafairBookingTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String[] AIRPORTS = {
            "Cape Town Int. Airport", "East London Airport", "George Airport",
            "Harare Int. Airport", "Hosea Kutako Int. Airport", "King Shaka Int. Airport",
            "Kruger Mpumalanga Int. Airport", "Lanseria Int. Airport", "Mauritius Int. Airport",
            "Port Elizabeth Int. Airport", "Victoria Falls Int. Airport", "Zanzibar Int. Airport"
    };



    @BeforeEach
    public void setUp() {


        // Path to my chromeDrive on my PC
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tiyan\\Downloads\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flysafair.co.za/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Navigated to FlySafair booking page.");


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flysafair.co.za/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Navigated to FlySafair booking page.");
    }

    @Test
    public void testBooking() {
        String from = getRandomAirport();
        String to = getRandomAirport(from);
        String departureDate = generateRandomDate();

        System.out.println("Testing booking from " + from + " to " + to + " on " + departureDate);

        fillBookingForm(from, to, departureDate);

        // Click search button
        driver.findElement(By.id("w6VifKkn")).click();

        // Wait for the results to load
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsId")));

        // Assert results are displayed
        assertTrue(results.isDisplayed(), "Results not displayed after valid booking.");
        System.out.println("Results displayed successfully.");
    }

    private void fillBookingForm(String from, String to, String departureDate) {
        driver.findElement(By.id("nmABXO")).sendKeys(from); // Replace with actual ID
        driver.findElement(By.id("CxVOHK")).sendKeys(to); // Replace with actual ID
        driver.findElement(By.id("eopMk7-trigger")).sendKeys(departureDate); // Replace with actual ID
    }

    private String getRandomAirport() {
        return AIRPORTS[new Random().nextInt(AIRPORTS.length)];
    }

    private String getRandomAirport(String exclude) {
        String airport;
        do {
            airport = getRandomAirport();
        } while (airport.equals(exclude));
        return airport;
    }

    private String generateRandomDate() {
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis();
        long randomOffset = (random.nextInt(30) + 1) * 24L * 60 * 60 * 1000; // Random within 30 days
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentTimeMillis + randomOffset));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed.");
        }
    }
}
