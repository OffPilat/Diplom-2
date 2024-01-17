package order;

import api.OrderClient;
import api.UserClient;
import constants.Api;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderTest {
    OrderClient orderClient = new OrderClient();
    UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Api.BASE_URL;
        userClient.successfulCreationUserWithBodyCheck();
    }

    @DisplayName("Тест на проверку API по созданию заказа")
    @Description("Проверка работы API по созданию заказов")
    @Test
    public void createOrderTest() {
        userClient.loginUser();
        orderClient.createOrder();
    }

    @DisplayName("Тест на проверку API по созданию заказа неавторизованным пользователем")
    @Description("Проверка работы API по созданию заказов")
    @Test
    public void createOrderNonAuthorizedUserTest() {
        orderClient.createOrderNonAuthorizedUser();
    }

    @DisplayName("Тест на проверку API по созданию заказа с ингредиентами")
    @Description("Проверка работы API по созданию заказов")
    @Test
    public void createOrderWithIngredientTest() {
        userClient.loginUser();
        orderClient.createOrderWithIngredient();
    }

    @DisplayName("Тест на проверку API по созданию заказа без ингредиентов")
    @Description("Проверка работы API по созданию заказов")
    @Test
    public void createOrderWithoutIngredientTest() {
        userClient.loginUser();
        orderClient.createOrderWithoutIngredient();
    }

    @DisplayName("Тест на проверку API по созданию заказа неверным хэшем ингредиентов")
    @Description("Проверка работы API по созданию заказов")
    @Test
    public void createOrderWithInvalidIngredientTest() {
        userClient.loginUser();
        orderClient.createOrderWithInvalidIngredient();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser();
    }
}
