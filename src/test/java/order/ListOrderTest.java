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

public class ListOrderTest {
    OrderClient orderClient = new OrderClient();
    UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Api.BASE_URL;
        userClient.successfulCreationUserWithBodyCheck();
    }

    @DisplayName("Тест на проверку API по получению списка заказа")
    @Description("Проверка работы API по получению списка заказов")
    @Test
    public void getOrderListAuthorizedUserTest() {
        userClient.loginUser();
        orderClient.getOrderListAuthorizedUser();
    }

    @DisplayName("Тест на проверку API по получению списка заказа неавторизованным пользователем")
    @Description("Проверка работы API по получению списка заказов")
    @Test
    public void getOrderListNonAuthorizedUserTest() {
        orderClient.getOrderListNonAuthorizedUser();
    }


    @After
    public void deleteUser() {
        userClient.deleteUser();
    }
}
