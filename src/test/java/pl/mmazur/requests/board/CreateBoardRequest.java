package pl.mmazur.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.secrets.TrelloSecrets;
import pl.mmazur.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardRequest(String boardName) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}
