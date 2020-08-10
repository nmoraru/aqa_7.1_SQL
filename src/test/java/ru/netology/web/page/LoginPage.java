package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validUser() {
        open("http://localhost:9999");
        loginField.sendKeys(DataGenerator.generateValidUser().getLogin());
        passwordField.sendKeys(DataGenerator.generateValidUser().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLoginUser() {
        open("http://localhost:9999");
        loginField.sendKeys(DataGenerator.generateInvalidUser().getLogin());
        passwordField.sendKeys(DataGenerator.generateValidUser().getPassword());
        loginButton.click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }

    public void invalidPasswordUser() {
        open("http://localhost:9999");
        loginField.sendKeys(DataGenerator.generateValidUser().getLogin());
        passwordField.sendKeys(DataGenerator.generateInvalidUser().getPassword());
        loginButton.click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }


}
