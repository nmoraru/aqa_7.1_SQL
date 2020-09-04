package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataGenerator.*;

public class CardTest {

    @AfterAll
    public static void cleanTables() {
        cleanData();
    }

    @Test
    void shouldValidUserAuth() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val validUser = generateValidUser();
        val verificationPage = loginPage.loginUser(validUser);
        verificationPage.isVisible();

        val validCode = generateValidVerificationCode();
        verificationPage.validVerify(validCode);
    }

    @Test
    void shouldInvalidLoginUser() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val invalidUser = generateInvalidUser();
        loginPage.loginUser(invalidUser);
        loginPage.invalidLogin();
    }

    @Test
    void shouldInvalidPasswordUser() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val invalidUser = generateValidUserWithInvalidPassword();
        loginPage.loginUser(invalidUser);
        loginPage.invalidLogin();
    }

    @Test
    void shouldInvalidVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val validUser = generateValidUser();
        val verificationPage = loginPage.loginUser(validUser);
        verificationPage.isVisible();

        val invalidCode = generateInvalidVerificationCode().toString();
        verificationPage.invalidVerify(invalidCode);
    }

    @Test
    void shouldBlockedVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val validUser = generateValidUser();
        val verificationPage = loginPage.loginUser(validUser);
        verificationPage.isVisible();

        val invalidCode = generateInvalidVerificationCode().toString();
        verificationPage.invalidVerify(invalidCode);
        verificationPage.invalidVerify(invalidCode);
        verificationPage.invalidVerify(invalidCode);

        verificationPage.blockedVerify(invalidCode);
    }

}

