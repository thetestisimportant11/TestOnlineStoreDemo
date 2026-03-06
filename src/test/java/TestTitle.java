import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTitle extends BazaTesta{

    @Test
    public void checkTitle () {
        driver.get("https://mega.readyscript.ru/");

        String expectedResult = ("ReadyScript Гипермаркет - демонстрационный интернет магазин");
        String actualResult = driver.getTitle();

        assertEquals(expectedResult, actualResult, "Название не соответствует ожиданию!");
    }
}
