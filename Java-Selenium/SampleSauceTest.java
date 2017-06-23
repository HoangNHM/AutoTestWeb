import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SampleSauceTest {

    public static final String USERNAME = "hoangnhm";
    public static final String ACCESS_KEY = "12345678";
    public static final String URL = "https://www.cybrary.it/video/viruses-worms-whiteboard/";
    public static final String login = "https://www.cybrary.it/wp-login.php?redirect_to=https%3A%2F%2Fwww.cybrary.it%2Fvideo%2Fviruses-worms-bintext-lab-2%2F%3Floggedout%3Dtrue";
    public static final String startPage = "https://www.cybrary.it/video/ids-firewalls-honeypots-intro/";
    private static String lastPage = startPage;

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        DesiredCapabilities caps = DesiredCapabilities.firefox();
//    caps.setCapability("platform", "Windows XP");
//    caps.setCapability("version", "43.0");

//    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        WebDriver driver = new FirefoxDriver(caps);

        /**
         * Goes to Sauce Lab's guinea-pig page and prints title
         */

        driver.get(login);
        driver.findElement(By.id("user_login")).sendKeys(USERNAME);
        driver.findElement(By.id("user_pass")).sendKeys(ACCESS_KEY);
        driver.findElement(By.id("wp-submit")).click();
        Thread.sleep(2000);
        driver.get(startPage);

        while (true) {
            if (!"https://www.cybrary.it/teams/".endsWith(driver.getCurrentUrl())) {
                lastPage = driver.getCurrentUrl();
            }
            System.out.println("url of page is: " + lastPage);
            try {
                work(driver);
            } catch (Exception e) {
                e.printStackTrace();
                driver.get(lastPage);
            }
        }

//    driver.quit();
    }

    public static void work(WebDriver driver) throws Exception {
        Thread.sleep(7000);
        WebElement elem = driver.findElement(By.id("lessforwd"));
//        Point location = elem.getLocation();
        Actions act = new Actions(driver);
        act.moveToElement(elem).moveByOffset(-50, 0).click().perform();
        Thread.sleep(6000);
        driver.findElement(By.id("lessforwd")).click();
        Thread.sleep(3000);
    }
}
