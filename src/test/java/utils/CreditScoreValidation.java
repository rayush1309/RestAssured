import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CreditScoreValidation {
    public static void main(String[] args) throws IOException {
        // Replace this with the correct path to your external JSON file
        String filePath = "resources/TestData/creditscore.json";

        // Read JSON payload from external file
        String jsonPayload = Files.readString(Path.of(filePath));

        // Parse JSON using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonPayload);

        // Extract people with credit score > 750
        List<String> highCreditScoreNames = getHighCreditScoreNames(jsonNode);

        // Print the names with high credit score
        System.out.println("Names with Credit Score > 750: " + highCreditScoreNames);

        // Perform actions or pass the names to another test method as needed
        anotherTestMethod(highCreditScoreNames);
    }

    private static List<String> getHighCreditScoreNames(JsonNode jsonNode) {
        List<String> highCreditScoreNames = new ArrayList<>();

        for (JsonNode personNode : jsonNode.path("people")) {
            int creditScore = personNode.path("creditscore").asInt();
            if (creditScore > 750) {
                String name = personNode.path("name").asText();
                highCreditScoreNames.add(name);
            }
        }

        return highCreditScoreNames;
    }

    private static void anotherTestMethod(List<String> names) {
        // Implement your logic for the second test method here
        System.out.println("Performing actions in another test method with names: " + names);
    }
}
