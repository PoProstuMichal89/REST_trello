package pl.mmazur.tests.board.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mmazur.requests.board.CreateBoardRequest;
import pl.mmazur.requests.card.CreateCardRequest;
import pl.mmazur.requests.lists.CreateListsRequest;
import pl.mmazur.tests.board.CreateBoardTest;

import java.util.HashMap;
import java.util.Map;

class MovedCardBetweenListsTest {

    private final String boardName = "Testowy board1";
    private final String firstListName = "FirstList";
    private final String secondListName = "SecondList";
    private String boardId;
    private String firstListId;
    private String secondListId;
    private String cardName = "My first card";
    private String cardId;

    @Test
        //testy e2e wg. kolejności ustalonje w scenariuszu
    void scenarioE2ETest() {
        createNewBoardTest();
        createFirstListTest();
        createSecondListTest();
        createCardOnFirstListTest();
    }


    void createNewBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);
        Assertions.assertThat(createBoardResponse.statusCode()).isEqualTo(200);
        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");

    }


    void createFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response createListResponse = CreateListsRequest.createListRequest(queryParams);
        Assertions.assertThat(createListResponse.statusCode()).isEqualTo(200);

        JsonPath json = createListResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(firstListName);
        firstListId = json.getString("id");

    }


    void createSecondListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", secondListName);
        queryParams.put("idBoard", boardId);

        final Response createListResponse = CreateListsRequest.createListRequest(queryParams);
        Assertions.assertThat(createListResponse.statusCode()).isEqualTo(200);

        JsonPath json = createListResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(secondListName);
        secondListId = json.getString("id");

    }

    void createCardOnFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", firstListId);
        queryParams.put("name", cardName);
        final Response response = CreateCardRequest.createCardRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(cardName);
        cardId = json.getString("id");
    }


}
