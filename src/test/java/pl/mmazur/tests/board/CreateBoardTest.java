package pl.mmazur.tests.board;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mmazur.requests.board.CreateBoardRequest;
import pl.mmazur.requests.board.DeleteBoardRequest;

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
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);

        Assertions.assertEquals(200, response.statusCode());
    }
}
