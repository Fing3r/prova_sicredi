import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Desafio01 {
    private static WebDriver driver;

    @BeforeClass
    public static void setUpTest(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.grocerycrud.com/demo/bootstrap_theme_v4");
    }

    @AfterClass
    public static void tearDownTest(){
        driver.quit();
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    @Test
    public void desafio01_addCustomerFormTest(){
        WebElement addCustomer = driver.findElement(By.linkText("Add Customer"));
        addCustomer.click();

        waitForLoad();

        WebElement customerName = driver.findElement(By.name("customerName"));
        customerName.sendKeys(("Teste Sicredi"));

        WebElement contactLastName = driver.findElement(By.name("contactLastName"));
        contactLastName.sendKeys(("Teste"));

        WebElement contactFirstName = driver.findElement(By.name("contactFirstName"));
        contactFirstName.sendKeys(("Lucas Finger"));

        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys(("51 9999-9999"));

        WebElement addressLine1 = driver.findElement(By.name("addressLine1"));
        addressLine1.sendKeys(("Av Assis Brasil, 3970"));

        WebElement addressLine2 = driver.findElement(By.name("addressLine2"));
        addressLine2.sendKeys(("Torre D"));

        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys(("Porto Alegre"));

        WebElement state = driver.findElement(By.name("state"));
        state.sendKeys(("RS"));

        WebElement postalCode = driver.findElement(By.name("postalCode"));
        postalCode.sendKeys(("91000-000"));

        WebElement country = driver.findElement(By.name("country"));
        country.sendKeys(("Brasil"));

        WebElement fromEmployeerSelect = driver.findElement(By.xpath("//*[@id=\"field_salesRepEmployeeNumber_chosen\"]"));
        fromEmployeerSelect.click();

        WebElement fixterSelectOption = driver.findElement(By.xpath("//*[@id=\"field_salesRepEmployeeNumber_chosen\"]/div/ul/li[8]"));
        fixterSelectOption.click();

        WebElement creditLimit = driver.findElement(By.name("creditLimit"));
        creditLimit.sendKeys(("200"));

        WebElement saveBtn = driver.findElement(By.id("form-button-save"));
        saveBtn.click();

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.className("go-to-edit-form")
                ));

        WebElement reportSuccessDiv = driver.findElement(By.id("report-success"));
        WebElement successMessageParagraph = reportSuccessDiv.findElement(By.tagName("p"));
        assertEquals("Your data has been successfully stored into the database. Edit Customer or Go back to list", successMessageParagraph.getText());

        // Sรณ para deixar a tela aberta um tempo e ver o que aconteceu
        //new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".classe-nao-existe"))).click();
    }
}
