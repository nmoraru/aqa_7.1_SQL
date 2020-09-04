package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public void isVisible(){
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        $(byText("Неверно указан код! Попробуйте ещё раз.")).waitUntil(Condition.visible, 5000);
    }

    public void blockedVerify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        $(byText("Превышено количество попыток ввода кода!")).waitUntil(Condition.visible, 5000);
    }

}
