package api;

import constants.Api;
import constants.Authorization;
import constants.ContentType;
import io.qameta.allure.Step;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserClient {
    private static final File createUser = new File("src/test/java/resources/createUser.json");
    private static final File createBadUser = new File("src/test/java/resources/createInvalidUser.json");
    private static final File loginUser = new File("src/test/java/resources/loginUser.json");
    private static final File loginInvalidUser = new File("src/test/java/resources/loginInvalidUser.json");
    private static final File editUser = new File("src/test/java/resources/editUser.json");

    @Step("Создание пользователя")
    public void createUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(createUser)
                .when()
                .post(Api.CREATE_USER)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Создание пользователя, который уже зарегистрирован")
    public void createExistUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(createUser)
                .when()
                .post(Api.CREATE_USER)
                .then()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Step("Создание пользователя без заполненного обязательного поля")
    public void createUserWithoutMandatoryField() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(createBadUser)
                .when()
                .post(Api.CREATE_USER)
                .then()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Логин пользователя")
    public void loginUser() {
        Authorization.ACCESS_TOKEN = given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginUser)
                .when()
                .post(Api.LOGIN_USER)
                .then()
                .statusCode(200)
                .extract().body().path("accessToken");
    }

    @Step("Логин пользователя с неверным логином и паролем")
    public void loginInvalidUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginInvalidUser)
                .when()
                .post(Api.LOGIN_USER)
                .then()
                .statusCode(401)
                .body("message", equalTo("email or password are incorrect"));
    }

    @Step("Изменение данных пользователя с авторизацией")
    public void editDataUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(editUser)
                .when()
                .patch(Api.PATCH_USER)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Изменение данных пользователя без авторизации")
    public void editDataNonAuthorizedUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginInvalidUser)
                .when()
                .patch(Api.PATCH_USER)
                .then()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }

    @Step("Удаление пользователя")
    public void deleteUser() {
        Authorization.ACCESS_TOKEN = given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(loginUser)
                .when()
                .post(Api.LOGIN_USER)
                .then()
                .extract().body().path("accessToken");
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .when()
                .delete(Api.PATCH_USER);

    }
}
