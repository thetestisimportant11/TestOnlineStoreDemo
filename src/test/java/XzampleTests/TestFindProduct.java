package XzampleTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestFindProduct extends BazaTesta{
    @Test
    public void testSuccessfulFoundProduct() {
            openWebSite();
            navigateToLoginPage();
            performLogin("test+2@gmail.com", "test");
            isLoginPasswordSuccessful();

            WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='поиск в каталоге']")));
            clickToFind.clear();
            clickToFind.sendKeys("Планшет Archos A9 PCtablet");

            WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='head-search__btn']")));
            pressClickToFind.click();

            isProductFounded();
        }
    @Test
    public void testNotFoundProduct(){
            openWebSite();
            navigateToLoginPage();
            performLogin("test+2@gmail.com", "test");
            isLoginPasswordSuccessful();

            WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='поиск в каталоге']")));
            clickToFind.click();
            clickToFind.sendKeys("");

            WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='head-search__btn']")));
            pressClickToFind.click();

            isProductNotFounded();
        }
}
