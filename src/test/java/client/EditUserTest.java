package client;

import api.UserClient;
import constants.Api;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditUserTest {
    UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Api.BASE_URL;
    }

    @DisplayName("Тест на проверку API по измению данных пользователя")
    @Description("Проверка работы API по изменению данных пользователей")
    @Test
    public void editDataUserTest() {
        userClient.successfulCreationUserWithBodyCheck();
        userClient.loginUser();
        userClient.editDataUser();
    }

    @DisplayName("Тест на проверку API по измению данных неавторизованного пользователя")
    @Description("Проверка работы API по изменению данных пользователей")
    @Test
    public void editDataNonAuthorizedUserTest() {
        userClient.editDataNonAuthorizedUser();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser();
    }

}
