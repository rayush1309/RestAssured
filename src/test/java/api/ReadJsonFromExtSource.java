package api;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadJsonFromExtSource {

    @Test
    public void verifyJsonContent() throws IOException {

        //body- expecting argument Type as String
        //content of the file to String-> content of the file  can convert into Byte
        //Byte Data to String
        try {
            given()
                    .when()
                    .log().all()
                    .body(new String(Files.readAllBytes(Paths.get("resources/TestData/creditscore.json"))))
                    .post("asynchPeople") // Replace with your API endpoint
                    .then()
                    .log().all()
                    .statusCode(200) // Replace with the expected status code
                    .body("some.json.path", equalTo("expected_value")); // Replace with your expected JSON path and value
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
