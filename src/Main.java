import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10 , SECONDS);
        driver.get("https://www.flipkart.com/");

        try {
            driver.findElement(By.xpath("//div[@class='_2QfC02']/button")).click();
        } catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
        try{
            driver.findElement(By.xpath("//input[@name='q']")).sendKeys("boat blue");
            List<WebElement> searchList = driver.findElements(By.xpath("//ul[contains(@class,'col-12-12')]//li/div/a/div[2]"));

            Actions actions = new Actions(driver);
            Thread.sleep(1000);
            actions.moveToElement(searchList.get(searchList.size()-1)).click().build().perform();

            System.out.println("FAssured Earphones Information:");
            HashMap<String, String> model = new HashMap<>();
            List<WebElement> fAssuredNames = driver.findElements(By.xpath("//div[@class='_32g5_j']/preceding-sibling::a[1]"));
            List<WebElement> fAssuredOPrice = driver.findElements(By.xpath("//div[@class='_32g5_j']/following-sibling::a[1]/div[1]/div[1]"));
            List<WebElement> fAssuredCPrice = driver.findElements(By.xpath("//div[@class='_32g5_j']/following-sibling::a[1]/div/div[2]"));
            List<WebElement> fAssuredDiscount = driver.findElements(By.xpath("//div[@class='_32g5_j']/following-sibling::a[1]/div/div[3]"));
            for(int i=1; i<=10;i++){
                System.out.print("Model "+ i +": " );
                model.put("Name",fAssuredNames.get(i).getText());
                model.put("Original Price",fAssuredOPrice.get(i).getText());
                model.put("Current Price",fAssuredCPrice.get(i).getText());
                model.put("Discount",fAssuredDiscount.get(i).getText());
                System.out.println(model);
            }
        } catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }


        driver.quit();

    }
}