package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

@Data
public class DataGenerator {

    public static void cleanData() {
        val runner = new QueryRunner();
        val codes = "DELETE FROM auth_codes";
        val transactions = "DELETE FROM card_transactions";
        val cards = "DELETE FROM cards";
        val users = "DELETE FROM users";

        try {
                val connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass");
            runner.update(connection, codes);
            runner.update(connection, transactions);
            runner.update(connection, cards);
            runner.update(connection, users);
        } catch (SQLException ex) {
            System.out.println("SQLException message:" + ex.getMessage());
        }
    }

    public static AuthInfo generateValidUser() {
        AuthInfo validUser = new AuthInfo(
                "vasya",
                "qwerty123");
        return validUser;
    }

    public static AuthInfo generateInvalidUser() {
        Faker faker = new Faker(new Locale("en"));
        AuthInfo invalidUser = new AuthInfo(
                faker.name().firstName().toLowerCase(),
                faker.internet().password());
        return invalidUser;
    }
    public static AuthInfo generateValidUserWithInvalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        AuthInfo invalidUser = new AuthInfo(
                "vasya",
                faker.internet().password());
        return invalidUser;
    }

    public static String generateValidVerificationCode() {
        val selectCode = "SELECT code FROM auth_codes WHERE created is not null;";
        try {
            val connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/app", "app", "pass");
            val countStmt = connection.createStatement();
            val result = countStmt.executeQuery(selectCode);

            if (result.next()) {
                val code = result.getString("code");
                return code;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException message:" + ex.getMessage());
        }
        return null;
    }

    public static VerificationInfo generateInvalidVerificationCode() {
        Faker faker = new Faker(new Locale("en"));
        VerificationInfo invalidVerificationCode = new VerificationInfo(
                faker.code().asin());
        return invalidVerificationCode;
    }


}
