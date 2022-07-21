package client;

import io.qameta.allure.Step;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseApiClient{

    @Step("Создание пользователя")
    public void createUser(User user) {
            given()
                .spec(getReqSpecJSON())
                .body(user)
                .when()
                .post(API_AUTH_REGISTER);
    }

    @Step("Получение токена")
    public String getAccessToken(User user) {
        return given()
                .spec(getReqSpecJSON())
                .body(user)
                .when()
                .post(API_AUTH_LOGIN)
                .jsonPath()
                .getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(User user) {
        String accessToken = getAccessToken(user);
            given()
                .spec(getReqSpecToken(accessToken))
                .when()
                .delete(API_AUTH_USER);
    }
}
