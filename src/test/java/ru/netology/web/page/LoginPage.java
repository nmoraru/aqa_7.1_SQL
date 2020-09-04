package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.AuthInfo;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage loginUser(AuthInfo user) {
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin() {
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }


}
