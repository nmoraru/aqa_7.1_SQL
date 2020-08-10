package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

import java.sql.SQLException;

import static ru.netology.web.data.DataGenerator.cleanData;

public class CardTest {

    @AfterAll
    public static void cleanTables() throws SQLException {
        cleanData();
    }

    @Test
    void shouldValidUserAuth() throws SQLException {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.validVerify();
    }

    @Test
    void shouldInvalidLoginUser() {
        val loginPage = new LoginPage();
        loginPage.invalidLoginUser();
    }

    @Test
    void shouldInvalidPasswordUser() {
        val loginPage = new LoginPage();
        loginPage.invalidPasswordUser();
    }

    @Test
    void shouldInvalidVerify() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.invalidVerify();
    }

    @Test
    void shouldBlockedVerify() throws SQLException {
        cleanData();

        shouldInvalidVerify();
        shouldInvalidVerify();
        shouldInvalidVerify();

        val loginPage = new LoginPage();
        val verificationPage = loginPage.validUser();
        verificationPage.blockedVerify();
    }

}
