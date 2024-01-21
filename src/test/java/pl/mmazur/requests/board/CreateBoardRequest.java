package pl.mmazur.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.secrets.TrelloSecrets;
import pl.mmazur.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardRequest(Map<String, String> queryParams)  {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}
