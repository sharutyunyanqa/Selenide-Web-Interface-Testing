
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
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федор Иванов");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void shouldTestNameNegativeLatin() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Fyorod Ivanov");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    public void shouldTestNegativeNameSymbols() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("@#!%^* |&^%");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldTestNegativeNameHieroglyph() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("阿利卡 阿利卡");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldTestNegativeNameNumbers() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("1235 8526");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldTestNegativeNameACIISymbols() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("♣☺☻♥♦ ♣♠•◘○");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldTestNegativeNameEmpty() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue(" ");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=name].input_invalid  .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        //$(By.className("input__sub")).shouldHave(exactText());

    }

    @Test
    public void shouldTestNegativePhoneLessThan11() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федр Иванов");
        form.$("[data-test-id=phone] input").setValue("+7235858");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));


    }

    @Test
    public void shouldTestNegativePhoneMoreThan11() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федр Иванов");
        form.$("[data-test-id=phone] input").setValue("+1236547456321");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldTestNegativePhoneWithoutPlus() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федр Иванов");
        form.$("[data-test-id=phone] input").setValue("96396396396");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldTestNegativePhoneNotJustNumbers() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федр Иванов");
        form.$("[data-test-id=phone] input").setValue("+996klj@#E");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldTestNegativeEmpty() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федр Иванов");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));


    }

    @Test
    public void shouldTestNegativeWithoutAgreementCheck() {
        open("http://localhost:7777");
        SelenideElement form = $(By.className("form_theme_alfa-on-white"));
        form.$("[data-test-id=name] input").setValue("Федор Иванов");
        form.$("[data-test-id=phone] input").setValue("+78528528528");
        form.$(By.className("button")).click();
        $(By.className("checkbox__text")).shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }
}


