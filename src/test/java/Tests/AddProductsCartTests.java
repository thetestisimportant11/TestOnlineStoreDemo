package Tests;

import Core.SeleniumForTest;
import Pages.DemoProductsPage;
import Pages.MainPage;
import org.junit.jupiter.api.Test;

public class AddProductsCartTests extends SeleniumForTest {

    @Test
    public void exampleAddProductCart(){
        DemoProductsPage demoProductsPage = new DemoProductsPage(driver, wait);
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.openLoginPage().auth("test+2@gmail.com", "test");
        mainPage.checkNameAfterLogin();

        mainPage.openDemoProductsPage().addProduct();
        demoProductsPage.checkAddProduct();

    }
    @Test
    public void selectTypeProductAndAddCart(){
        DemoProductsPage demoProductsPage = new DemoProductsPage(driver, wait);
        MainPage mainPage = new MainPage(driver,wait);

        mainPage.openLoginPage().auth("test+2@gmail.com", "test");
        mainPage.checkNameAfterLogin();

        mainPage.openDemoProductsPage().moveButtonAddProduct();
        demoProductsPage.checkAddProduct();

    }
}
