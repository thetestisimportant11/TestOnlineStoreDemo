import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFindProduct extends BazaTesta{
        @Test
        public void testSuccessfulFoundProduct() {
            driver.get("https://mega.readyscript.ru/");
            System.out.println("Страница загружена");

            navigateToLoginPage();
            performLogin("demo@example.com", "demo");

            isLoginSuccessful();

            WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='поиск в каталоге']")));
            clickToFind.clear();
            clickToFind.sendKeys("Планшет Archos A9 PCtablet");

            WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='head-search__btn']")));
            pressClickToFind.click();

            assertTrue(isProductFounded(), "Поиск не сработал");
        }
        @Test
        public void testNotFoundProduct(){
            driver.get("https://mega.readyscript.ru/");
            System.out.println("Страница загружена");

            navigateToLoginPage();
            performLogin("demo@example.com", "demo");

            isLoginSuccessful();

            WebElement clickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='поиск в каталоге']")));
            clickToFind.click();
            clickToFind.sendKeys("");

            WebElement pressClickToFind = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='head-search__btn']")));
            pressClickToFind.click();

            assertTrue(isProductNotFounded(), "При отправке пустого значения что-то нашлось(");

        }

        public boolean isProductFounded() {
            try {
                WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),' Результаты поиска')]")));
                System.out.println("Поиск выполнен, показан результат");
                return userElement.isDisplayed();
            } catch (TimeoutException e) {
                return false;
            }
        }
        public boolean isProductNotFounded() {
            try {
                WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(text(),'По вашему запросу ничего не найдено. Проверьте правильность введенного запроса')]")));
                System.out.println("Поиск выполнен, показан результат отсутствия искомого товара");
                return userElement.isDisplayed();
            } catch (TimeoutException e) {
                return false;
            }
        }
}
