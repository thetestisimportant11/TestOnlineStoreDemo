import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCatalog extends BazaTesta {
    @Test
    public void UsingCatalog(){
        driver.get("https://mega.readyscript.ru/");
        System.out.println("Страница загружена");

        navigateToLoginPage();
        performLogin("demo@example.com", "demo");

        isLoginSuccessful();

        WebElement clickCatalog = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Каталог')]")));
        clickCatalog.click();

        assertTrue(isCatalogIsVisible(),"Категории не отображаются");

        WebElement clickCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Демо-продукты')]")));
        clickCategory.click();

        assertTrue(isSuccessfulUsingCatalog(),"Выбранная категория не отобразилась");

    }
    public boolean isCatalogIsVisible(){
        try{
            WebElement clickCatalogProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Выберите')][contains(text(),'категорию')]")));
            System.out.println("Каталог открыт, доступен выбор категории товаров");

            return clickCatalogProduct.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSuccessfulUsingCatalog(){
        try{
            WebElement checkOpenCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),'Демо-продукты')]")));
            System.out.println("Выбранная категория отобразилась");
            return checkOpenCategory.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
