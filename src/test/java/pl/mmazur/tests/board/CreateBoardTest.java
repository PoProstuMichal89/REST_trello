package pl.mmazur.tests.board;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mmazur.requests.board.CreateBoardRequest;
import pl.mmazur.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateBoardTest {

    private final String boardName = "Testowy board1";
    private String boardId;


    @Test
    void createBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

//        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
//        Assertions.assertEquals(boardName, json.getString("name"));
        Assertions.assertThat((json.getString("name"))).isEqualTo(boardName);
        boardId = json.getString("id");

        //Delete board
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);

//        Assertions.assertEquals(200, deleteResponse.statusCode());
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
