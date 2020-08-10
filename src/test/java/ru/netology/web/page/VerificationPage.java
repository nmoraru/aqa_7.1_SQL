package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;


public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify() throws SQLException {
        codeField.setValue(DataGenerator.generateValidVerificationCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify() {
        codeField.setValue(DataGenerator.generateInvalidVerificationCode().getCodeFromSMS());
        verifyButton.click();
        $(byText("Неверно указан код! Попробуйте ещё раз.")).waitUntil(Condition.visible, 5000);
    }

    public void blockedVerify() {
        codeField.setValue(DataGenerator.generateInvalidVerificationCode().getCodeFromSMS());
        verifyButton.click();
        $(byText("Превышено количество попыток ввода кода!")).waitUntil(Condition.visible, 5000);
    }

}
