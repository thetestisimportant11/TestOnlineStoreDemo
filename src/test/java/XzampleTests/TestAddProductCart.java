package XzampleTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestAddProductCart extends BazaTesta{


    @Test
    public void isAddProductCart() {
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "test");
        isLoginPasswordSuccessful();

        WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[text()='Каталог']")));
        clickToFind.click();

        WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Демо-продукты']")));
        pressClickToFind.click();

        WebElement clickAddCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-href='/cart/?add=46&offer_id=1']")));
        clickAddCart.click();

        successAddCart();
    }
    @Test
    public void isAddProductCartRadioList() {
        Actions actions = new Actions(driver);
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "test");
        isLoginPasswordSuccessful();

        WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[text()='Каталог']")));
        clickToFind.click();

        WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Демо-продукты']")));
        pressClickToFind.click();

        WebElement moveAddCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-href='/cart/?add=46&offer_id=1']")));
        actions.moveToElement(moveAddCart).perform(); // Наводим курсор на кнопку "В корзину", должен появиться список для выбора комплектации товара
        System.out.println("Наведение на кнопку выполнено");

        WebElement radioList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='1-46--2']")));
        actions.moveToElement(radioList).perform();
        radioList.click();

        moveAddCart.click();

        successAddCart();
    }
    public void successAddCart(){
        try {
            WebElement checkCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Перейти в корзину']")));
            checkCart.isDisplayed();
            System.out.println("Появилось всплывающее окно корзины");
        } catch (TimeoutException e){
            System.out.println("Всплывающее окно корзины не появилось");
        }
    }
    }
