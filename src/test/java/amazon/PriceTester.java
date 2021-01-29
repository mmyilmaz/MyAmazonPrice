package amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class PriceTester {
    String productID;


        public static String getPrice(String productID) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.de");
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(productID);
        driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
        String price = driver.findElement(By.xpath("//span[@class=\"a-price-whole\"]")).getText();
        driver.quit();
        return price;
    }

    @Test
    public void Test1() {

    try {
    String query = "SELECT * FROM account";
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazonprices?useTimezone=true&serverTimezone=UTC", "root", "Mtk.202123733");
    PreparedStatement pst = con.prepareStatement(query);
    ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                String PriceInMySQL = rs.getString(2);
                System.out.println("PriceInMySQL = " + PriceInMySQL);
                String ProducktID = rs.getString(1);
                System.out.println("ProducktID = " + ProducktID);
                String PriceInWeb = getPrice(ProducktID);
                System.out.println("PriceInWeb = " + PriceInWeb);
            }
    } catch (Exception e) {
    e.printStackTrace();
    }
}


}







