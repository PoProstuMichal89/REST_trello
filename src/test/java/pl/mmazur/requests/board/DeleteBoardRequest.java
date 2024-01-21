package pl.mmazur.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.secrets.TrelloSecrets;
import pl.mmazur.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {
    public static Response deleteBoardRequest(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
