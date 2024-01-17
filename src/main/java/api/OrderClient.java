package api;

import constants.Api;
import constants.Authorization;
import constants.ContentType;
import io.qameta.allure.Step;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderClient {
    private static final File createOrder = new File("src/test/java/resources/createOrder.json");
    private static final File createEmptyOrder = new File("src/test/java/resources/createEmptyOrder.json");
    private static final File createInvalidOrder = new File("src/test/java/resources/createInvalidOrder.json");


    @Step("Создание заказа с авторизацией")
    public void createOrder() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(createOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Создание заказа без авторизации")
    public void createOrderNonAuthorizedUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(createOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .body("success", equalTo(false));
    }

    @Step("Создание заказа с ингредиентами")
    public void createOrderWithIngredient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(createOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(createEmptyOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(400)
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Создание заказа с неверным хешем ингредиентов")
    public void createOrderWithInvalidIngredient() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(createInvalidOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(500);
    }

    @Step("Получение заказов авторизованного пользователя")
    public void getOrderListAuthorizedUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .header(Authorization.AUTHORIZATION, Authorization.ACCESS_TOKEN)
                .body(createOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Step("Получение заказов неавторизованного пользователя")
    public void getOrderListNonAuthorizedUser() {
        given()
                .contentType(ContentType.CONTENT_TYPE)
                .body(createOrder)
                .when()
                .post(Api.ORDER_ENDPOINT)
                .then()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }
}
