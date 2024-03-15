package restAssuredApiFramewok;

import com.google.gson.JsonObject;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ValidatePostWithMap_JosnObject_requestBodyDataProvider {
        @Test
        public void validatePostWithString(){
            RestAssured.baseURI="https://reqres.in";
            JsonObject requestBody= new JsonObject();
            requestBody.addProperty("name","Ayush-Raj");
            requestBody.addProperty("job","Software-Engineer");


            Response response=
                    given()
                            .header("Content-Type","application/json")
                            .body(requestBody.toString())
                            .when()
                            .post("/api/users")
                            .then()
                            .extract()
                            .response();
            System.out.println(response.getBody().asString());
            Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);


        }

        @DataProvider(name = "userData")
        public Object[][] userData() {
            return new Object[][] {
                    {"Ayush-Raj", "Software-Engineer"},
                    {"John-Doe", "Data-Scientist"},
                    // Add more sets of data as needed
            };
        }

        @Test(dataProvider = "userData")
        public void validatePostWithString(String name, String job) {
            RestAssured.baseURI = "https://reqres.in";
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("name", name);
            requestBody.addProperty("job", job);

            Response response =
                    given()
                            .header("Content-Type", "application/json")
                            .body(requestBody.toString())
                            .when()
                            .post("/api/users")
                            .then()
                            .extract()
                            .response();
            System.out.println(response.getBody().asString());
            Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        }
    }

