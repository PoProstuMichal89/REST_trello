package pl.mmazur.tests.board;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import pl.mmazur.requests.board.DeleteBoardRequest;


public class DeleteBoardTest {
    void deleteBoardRequest(String boardId) {
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

}
