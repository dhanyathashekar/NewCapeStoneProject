
package scenario1;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HrmTest 
{
  @Test(dataProvider ="exceldata",dataProviderClass = CustomData.class)
  public void login(String un,String psw) throws InterruptedException
  {
	  WebDriver driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  
	  //username
	  driver.findElement(By.name("username")).sendKeys(un);
	  //password
	  
	  driver.findElement(By.name("password")).sendKeys(psw);
	  
	  //login
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  Thread.sleep(2000);
	  Utility.getScreenshot(driver);
	  
	  
	  //validation
	  Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"Login Fail");
	  System.out.println("Login Pass!");
	  
	  //logout
	  driver.close();	  
	  
  }
  
}
