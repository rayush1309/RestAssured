package restAssuredApiFramewok;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class ValidateMultipleHeadersUsingMap {
    @Test
    public void validateHeadersUsingMap(){
        Map<String,String>map =new HashMap<>();
        map.put("application/json","application/json");
        map.put("Authorization", "Bearer your_access_token");
        map.put("Connection","keep-alive");
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        given()
                .headers(map)
                .log().all()
                .when()
                .get("posts/1")
                .then()
                .statusCode(200);
        System.out.println("Multiple Headers using Map Automated Successfully");
    }

}
