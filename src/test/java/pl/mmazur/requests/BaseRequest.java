package pl.mmazur.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.mmazur.secrets.TrelloSecrets;

public class BaseRequest {
    protected static RequestSpecBuilder requestSpecBuilder;

    public static RequestSpecification requestSetup() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addQueryParam("key", TrelloSecrets.getKey());
        requestSpecBuilder.addQueryParam("token", TrelloSecrets.getToken());

        return requestSpecBuilder.build();
    }
}
