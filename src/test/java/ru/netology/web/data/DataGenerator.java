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

    public static void cleanData() throws SQLException {
        val runner = new QueryRunner();
        val codes = "DELETE FROM auth_codes";

        try (
                val connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            runner.update(connection, codes);
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

    public static String generateValidVerificationCode() throws SQLException {
        val selectCode = "SELECT code FROM auth_codes WHERE created is not null;";
        val connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        val countStmt = connection.createStatement();

        val result = countStmt.executeQuery(selectCode);

        if (result.next()) {
            val code = result.getString("code");
            return code;
        }
        return null;
    }

    public static VerificationInfo generateInvalidVerificationCode() {
        return new VerificationInfo("123");
    }


}
