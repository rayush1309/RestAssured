import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader {
    public static void main(String[] args) throws IOException {
        // Replace this with the correct path to your external JSON file
        String filePath = "resources/TestData/creditscore.json";

        // Read JSON payload from external file
        String jsonPayload = Files.readString(Path.of(filePath));

        // Print the JSON payload to the console
        System.out.println(jsonPayload);

        // Parse JSON using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonPayload);

        // Use the parsed JSON as the request body (assuming REST Assured usage)
        RestAssured.given()
                .body(jsonNode.toString())
                .log().all()
                // Add other request specifications and send the request
                .when()
                .post("your-api-endpoint");
    }
}
