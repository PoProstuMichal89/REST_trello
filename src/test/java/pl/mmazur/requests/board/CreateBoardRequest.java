package pl.mmazur.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.mmazur.secrets.TrelloSecrets;
import pl.mmazur.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardRequest(String boardName) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloSecrets.getKEY())
                .queryParam("token", TrelloSecrets.getTOKEN())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}
