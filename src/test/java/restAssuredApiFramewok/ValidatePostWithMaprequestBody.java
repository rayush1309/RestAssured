package restAssuredApiFramewok;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ValidatePostWithMaprequestBody {
        @Test
        public void validatePostWithString(){
            RestAssured.baseURI="https://reqres.in";
            JsonObject req= new JsonObject();
            req.addProperty("name","ayush");
            req.addProperty("job","Software-Engineer");


            Response response=
                    given()
                            .header("Content-Type","application/json")
                            .body(req.toString())
                            .when()
                            .post("/api/users")
                            .then()
                            .extract()
                            .response();
            System.out.println(response.getBody().asString());
//        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
//        System.out.println(response.getBody().asString());
//        System.out.println("PostWithString Executed sucessfully");


        }

}
