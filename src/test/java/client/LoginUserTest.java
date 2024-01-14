package client;

import api.UserClient;
import constants.Api;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {
    UserClient userClient = new UserClient();


    @Before
    public void setUp() {
        RestAssured.baseURI = Api.BASE_URL;
        userClient.createUser();
    }

    @DisplayName("Тест на проверку API по авторизации пользователя")
    @Description("Проверка работы API по авторизации пользователей")
    @Test
    public void loginUserTest() {
        userClient.loginUser();
    }

    @DisplayName("Тест на проверку API по авторизации невалидного пользователя")
    @Description("Проверка работы API по авторизации пользователей")
    @Test
    public void loginInvalidUserTest() {
        userClient.loginInvalidUser();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser();
    }
}
