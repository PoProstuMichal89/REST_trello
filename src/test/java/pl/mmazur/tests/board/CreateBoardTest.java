package pl.mmazur.tests.board;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mmazur.requests.board.CreateBoardRequest;
import pl.mmazur.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CreateBoardTest {

    private final String boardName = "Testowy board1";
    private String boardId;


    @Test
    void createBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

//        Assertions.assertEquals(200, response.statusCode()); --> asercja z junita
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
//        Assertions.assertEquals(boardName, json.getString("name")); -> asercja z junita
        Assertions.assertThat((json.getString("name"))).isEqualTo(boardName);
        boardId = json.getString("id");

        //Delete board
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);

//        Assertions.assertEquals(200, deleteResponse.statusCode()); -> asercja z junita
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    @DisplayName("Create board with valid data")
    @ParameterizedTest(name = "Board name: {0}") //wyświetla nazwę testu zamiast nazwy metody
    @MethodSource("sampleBoardNameData")
    void createBoard1Test(String boardName) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response response = CreateBoardRequest.createBoardRequest(queryParams);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
        Assertions.assertThat((json.getString("name"))).isEqualTo(boardName);
        boardId = json.getString("id");

        //Delete board
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    //metoda zwraca strumień danych,  używany potem w metodzie createBoard1Test do podstawiania danych testowych
    private static Stream<Arguments> sampleBoardNameData() {
        return Stream.of(
                Arguments.of("Testowy Board"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("Testowa1"),
                Arguments.of("TESTOWA2")
        );
    }


}
