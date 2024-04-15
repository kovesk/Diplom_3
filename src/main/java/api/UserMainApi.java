package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import constants.PathsForApi;
import requests.AuthCreds;
import requests.UserCreds;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class UserMainApi {

    private final Header contentTypeHeader = new Header(CONTENT_TYPE, APPLICATION_JSON.getMimeType());

    public UserMainApi() {
        RestAssured.baseURI = PathsForApi.BASE_URL;
    }

    @Step("Создание УЗ")
    public String userCreate(UserCreds userCreds) {
        Response response = given()
                .header(contentTypeHeader)
                .body(userCreds)
                .when()
                .post(PathsForApi.REGISTRATION_PATH);

        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Авторизация пользователя")
    public String userAuth(AuthCreds authCreds) {
        Response response = given()
                .header(contentTypeHeader)
                .body(authCreds)
                .when()
                .post(PathsForApi.LOGIN_PATH);
        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void userDelete(String accessToken) {
        Header authHeader = new Header(AUTHORIZATION, accessToken);
        given()
                .header(authHeader)
                .when()
                .delete(PathsForApi.AUTHORISATION_PATH);
    }
}
