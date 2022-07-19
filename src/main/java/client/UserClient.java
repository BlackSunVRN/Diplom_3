package client;

import io.qameta.allure.Step;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseApiClient{

    @Step("Получение токена")
    public String getAccessToken(User user) {
        return given()
                .spec(getReqSpecJSON())
                .body(user)
                .when()
                .post("/api/auth/login")
                .jsonPath()
                .getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(User user) {
        String accessToken = getAccessToken(user);
            given()
                .spec(getReqSpecToken(accessToken))
                .when()
                .delete("/api/auth/user");
    }


}