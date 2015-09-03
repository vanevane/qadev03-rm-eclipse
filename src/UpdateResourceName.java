import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UpdateResourceName {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://172.20.208.79:4040/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testUpdatename() throws Exception {
	    driver.findElement(By.id("loginUsername")).clear();
	    driver.findElement(By.id("loginUsername")).sendKeys("rmdom2008\\room.manager");
	    driver.findElement(By.id("loginPassword")).clear();
	    driver.findElement(By.id("loginPassword")).sendKeys("M@nager");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.linkText("Resources"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.linkText("Resources")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | css=div.ng-scope > span.ng-binding | ]]
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("(//input[@type='text'])[3]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("newResourceEdit");
	    driver.findElement(By.cssSelector("button.info")).click();
	    driver.findElement(By.linkText("Conference Rooms")).click();
	    driver.findElement(By.linkText("Resources")).click();
	    assertEquals("newResourceEdit", driver.findElement(By.cssSelector("div.ngCell.centeredColumn.col2.colt2")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
