package pl.mmazur.tests.board;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mmazur.secrets.TrelloSecrets;
import pl.mmazur.url.TrelloUrl;
import pl.mmazur.requests.board.CreateBoardRequest;

import static io.restassured.RestAssured.given;

public class CreateBoardTest {

    private final String boardName = "Testowy board1";
    private String boardId;


    @Test
    void createBoardTest() {

        final Response response = CreateBoardRequest.createBoardRequest(boardName);

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));
        boardId = json.getString("id");

        //Delete board
        given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloSecrets.getKEY())
                .queryParam("token", TrelloSecrets.getTOKEN())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
    }
}
