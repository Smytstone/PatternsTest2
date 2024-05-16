package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.utility.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class DataGenerationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void memoryClear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void userShouldBeActive() {
        var validUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] .input__box .input__control").val(validUser.getLogin());
        $("[data-test-id=password] .input__box .input__control").val(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("h2").shouldHave(Condition.exactText("  Личный кабинет"));

    }

    @Test
    void userShouldBeBlocked() {
        var validUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] .input__box .input__control").val(validUser.getLogin());
        $("[data-test-id=password] .input__box .input__control").val(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Пользователь заблокирован"));
    }

    @Test
    void shouldPassTestCase1() {
        var invalidUser = DataGenerator.Registration.getUser("blocked");
        $("[data-test-id=login] .input__box .input__control").val(DataGenerator.getRandomLogin());
        $("[data-test-id=password] .input__box .input__control").val(invalidUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldPassTestCase2() {
        var invalidUser = DataGenerator.Registration.getUser("blocked");
        $("[data-test-id=login] .input__box .input__control").val(invalidUser.getLogin());
        $("[data-test-id=password] .input__box .input__control").val(DataGenerator.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldPassTestCase3() {
        $("[data-test-id=login] .input__box .input__control").val(DataGenerator.getRandomLogin());
        $("[data-test-id=password] .input__box .input__control").val(DataGenerator.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldPassTestCase4() {
        var validUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] .input__box .input__control").val(DataGenerator.getRandomLogin());
        $("[data-test-id=password] .input__box .input__control").val(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldPassTestCase5() {
        var validUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] .input__box .input__control").val(validUser.getLogin());
        $("[data-test-id=password] .input__box .input__control").val(DataGenerator.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! " + "Неверно указан логин или пароль"));
    }

    @Test
    void shouldPassTestCase6() {
        var validUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=password] .input__box .input__control").val(validUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPassTestCase7() {
        var validUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] .input__box .input__control").val(validUser.getLogin());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldPassTestCase8() {
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
        $("[data-test-id=password].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
}
