package client;

import io.qameta.allure.Description;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApiClient {

    @Description("Спецификация для использования JSON в запросе")
    public static RequestSpecification getReqSpecJSON() {
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON).build();
    }

    @Description("Спецификация для использования Bearer-токена в запросе")
    public static RequestSpecification getReqSpecToken(String bearerToken) {
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .log(LogDetail.ALL)
                .build().header("Authorization", bearerToken);
    }
}
