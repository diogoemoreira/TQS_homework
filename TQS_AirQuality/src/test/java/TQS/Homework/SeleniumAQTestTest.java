package TQS.Homework;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

public class SeleniumAQTestTest {
  private WebDriver driver;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/bin/geckodriver");
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void seleniumAQTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().maximize();
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("Oporto");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
    assertThat(driver.findElement(By.cssSelector(".location")).getText(), is("Sobreiras-Lordelo do Ouro, Porto, Portugal"));
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("lisbon");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("shangai");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("oporto");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("portugal");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
    driver.findElement(By.id("cityToFind")).click();
    driver.findElement(By.id("cityToFind")).sendKeys("lisbon");
    driver.findElement(By.cssSelector(".buttonsubmit")).click();
  }
}
