package pl.mmazur.requests.card;

import io.restassured.response.Response;
import pl.mmazur.requests.BaseRequest;
import pl.mmazur.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {
    public static Response updateCardRequest(Map<String, String> queryParams, String cardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}
