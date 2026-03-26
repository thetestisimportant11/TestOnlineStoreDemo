package XzampleTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCatalog extends BazaTesta {
    @Test
    public void testUsingCatalog(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "test");
        isLoginPasswordSuccessful();

        WebElement clickCatalog = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Каталог')]")));
        clickCatalog.click();

        isCatalogIsVisible();

        WebElement clickCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Демо-продукты')]")));
        clickCategory.click();

        isSuccessfulUsingCatalog();
    }
}
