package pl.mmazur.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
        requestSpecBuilder.addFilter(new RequestLoggingFilter());
        requestSpecBuilder.addFilter(new ResponseLoggingFilter()); // --> dodawanie logowania requestów i response'ów (wszystkich, nie tylko błędnych

        return requestSpecBuilder.build();
    }
}
