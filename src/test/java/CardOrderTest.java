import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @Test
    public void shouldTestPositive() {
        open("http://localhost:7777");
        SelenideElement form =$(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input") .setValue("Федор Иванов");
        form.$("[data-test-id=phone] input") .setValue("+78528528528");
        form.$("[data-test-id=agreement]") .click();
        form.$(By.className("button" )) .click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Configuration.timeout = 6000;
    }
}

