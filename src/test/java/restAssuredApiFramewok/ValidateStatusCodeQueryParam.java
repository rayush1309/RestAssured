package restAssuredApiFramewok;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class


ValidateStatusCodeQueryParam {
    @Test
    public void validateStatusCode() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://reqres.in";
        // Send a GET request with a query parameter "page" set to 2 and store the response
        Response resp =
                given()
                        .queryParam("page", 2)
                        .when()
                        .get("/api/users");
        // Extract the actual status code from the response using RestAssured
        int actualStatusCode = resp.statusCode();
        // Assert that the actual status code is equal to the expected status code (200)
        assertEquals(actualStatusCode, 200); // Using TestNG assertion
        // Note: If the status code is not equal to 200, the assertion will fail and the test will be marked as failed.
    }

}
