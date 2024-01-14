package client;

import api.UserClient;
import constants.Api;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;

public class CreationUserTest {
    UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Api.BASE_URL;
    }

    @DisplayName("Тест на проверку API по созданию пользователя")
    @Description("Проверка работы API по созданию пользователей")
    @Test
    public void createUserTest() {
        userClient.createUser();
        userClient.deleteUser();
    }

    @DisplayName("Тест на проверку API по созданию уже существующего пользователя")
    @Description("Проверка работы API по созданию пользователей")
    @Test
    public void createExistUserTest() {
        userClient.createUser();
        userClient.createExistUser();
        userClient.deleteUser();
    }

    @DisplayName("Тест на проверку API по созданию пользователя без обязательно заполненного поля")
    @Description("Проверка работы API по созданию пользователей")
    @Test
    public void createUserWithoutMandatoryFieldTest() {
        userClient.createUserWithoutMandatoryField();
    }


}
