package ru.netology.utility;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.utility.DataGenerationInfo;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void sendRequest(DataGenerationInfo user) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new Gson().toJson(user)) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static String getRandomLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String getRandomPassword() {
        String password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static DataGenerationInfo getUser(String status) {
            DataGenerationInfo user = new DataGenerationInfo(getRandomLogin(), getRandomPassword(), status);
            return user;
        }

        public static DataGenerationInfo getRegisteredUser(String status) {
            DataGenerationInfo registeredUser = getUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }
    }


}