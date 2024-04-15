package pl.mmazur.requests.board;

import io.restassured.response.Response;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class ReadExistingBoard {
    public static Response readExistingBoard(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .get(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
