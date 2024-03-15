package restAssuredApiFramewok;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class End2EndAutomation {
    @Test
    public void getResponseBody() {
        // Set base URI for the API
        RestAssured.baseURI = "https://stageapp.tekioncloud.xyz";
        given().when().get("api/wms/parts/u/inventory/utility/extended/report/compute/all-sites")
                .then()
                .statusCode(401)
                .body(not(isEmptyString()))
                .assertThat()
                .body("message", equalTo("Token doesn't exist or is invalid"))
                .body("status", equalTo(StatusCode.UNAUTHORIZED.code));
    }
    @Test
    public void getResponseBody2() {
        // Set base URI for the API
        RestAssured.baseURI = "https://stageapp.tekioncloud.xyz";
        given().when().get("api/wms/parts/u/inventory/utility/extended/report/compute/all-sites")
                .then()
                .statusCode(401)
                .assertThat()
                .body("message", equalTo("Token doesn't exist or is invalid"))
                .body("status", equalTo(StatusCode.UNAUTHORIZED.code));
    }
    @Test
    public void validateResponseBodyGetPathParam() {
        // Set the value for the path parameter 'seasonvalue'
        String seasonValue = "2017";
        // Send a GET request with a path parameter and store the response
        Response resp = given()
                .pathParam("seasonvalue", seasonValue)  // Provide parameter name and value
                .when()
                .get("http://ergast.com/api/f1/{seasonvalue}/circuits.json");
        // Extract the status code from the response
        int actualStatusCode = resp.statusCode();
        // Assert that the status code is equal to the expected value (200)
        assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        // Print the response body to the console
        System.out.println(resp.body().asString());
    }
    @Test
    public void validateBasicAuthentication(){
        // Set the base URI for the RestAssured requests
        RestAssured.baseURI = "https://postman-echo.com";
        // Send a GET request with basic authentication
        Response response =
                given()
                        .auth()
                        .basic("postman", "password")
                        .when()
                        .get("/basic-auth");
        // Retrieve the actual status code from the response
        int actualStatusCode = response.statusCode();
        // Assert that the actual status code is equal to the expected status code (200)
        assertEquals(actualStatusCode,StatusCode.SUCCESS.code );
        // Print the response body as a string
        System.out.println(response.body().asString());
    }
    //DigestAuth :
    @Test
    public void validateDigestAuthenticationInResponse() {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response =
                given()
                        .auth()
                        .digest("postman", "password")
                        .when()
                        .get("/digest-auth");
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }
    @Test
    public void validateCookie(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        given()
                .cookie("cookie1","test1")
                .cookie("cookie1","test2")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
        System.out.println("Cookie Validation executed Successfully");
    }
    @Test
    public void validateCookie2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .cookie("cookie1", "test1")
                .cookie("cookie1", "test2")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
        System.out.println("Cookie Validation executed Successfully");
    }
    @Test
    public void testCookieUsingCookieBuilder() {
        // Set the base URI for your API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // Create a Cookie object
        Cookie cookies = new Cookie.Builder("cookieKey1", "cookieValue1")
                .setComment("using Cookie key")
                .build();
        // Perform a GET request using the specified cookies
        given()
                .cookie(cookies)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
        System.out.println("Cookie Validation executed Successfully using Cookie Builder");
    }
    /*
    @Test
    public void fetchCookieFromResponse(){
        // Set the base URI for your API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // Perform a GET request to the endpoint "/posts/1" and store the response
        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        // Get all the cookies from the response and store them in a Map
        Map<String,String> map = response.getCookies();
        // Get detailed information about all the cookies from the response
        Cookies cookie = response.getDetailedCookies();
        // Get the value of the "Server" cookie from the detailed cookies
        cookie.getValue("Server");
        // Assert that the Map of cookies has a key named "3638382Ayys73"
        assertThat(map, hasKey("3638382Ayys73"));
        // Assert that the Map of cookies has a value "test72773"
        assertThat(map, hasValue("test72773"));
    }

     */
    @Test
    public void validateDeleteStatusCode(){
        Response resp= given()
                .delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), StatusCode.NO_CONTENT.code);
        System.out.println("Delete Method Executed Sucessfully"+"No Content: "+"204 Status Code");
    }
    @Test
    public void responseFirstListDataValidation(){
        // Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";
        // Send a GET request and store the response in a variable
        // Sending GET Request and Extracting Response:
        Response response =
                given()
                        .queryParam("page",2)  // Adding query parameter "page" with a value of 2 to the request
                        .when()
                        .get("/users")  // Making a GET request to the "/users" endpoint
                        .then()
                        .extract()
                        .response();  // Extracting the response for further validation
        // Validating specific data in the response using Hamcrest matchers
        response.then().body("data[1].id", is(8));  // Checking if the value of "id" in the response matches the expected value 8
        response.then().body("data[1].email", is("lindsay.ferguson@reqres.in"));  // Checking if the value of "email" matches the expected value
        response.then().body("data[1].first_name", is("Lindsay"));  // Checking if the value of "first_name" matches the expected value "Lindsay"
        response.then().body("data[1].last_name", is("Ferguson"));  // Checking if the value of "last_name" matches the expected value "Ferguson"
        response.then().body("data[1].avatar", is("https://reqres.in/img/faces/8-image.jpg"));  // Checking if the value of "avatar" matches the expected value
    }
    @Test
    public void validateMultipleHeaders(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        given()
                .header("application/json","application/json")
                .header("Authorization","Bearer your_access_token")
                .when()
                .get("posts/1")
                .then()
                .statusCode(200);
        System.out.println("Multiple Headers Automated Successfully");
    }
    @Test
    public void validateHeadersUsingMap(){
        Map<String,String>map =new HashMap<>();
        map.put("application/json","application/json");
        map.put("Authorization", "Bearer your_access_token");
        map.put("Connection","keep-alive");
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        given()
                .headers(map)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200);
        System.out.println("Multiple Headers using Map Automated Successfully");
    }
    @Test
    public void validateresponseSize() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        //send Get request and store the response in a varriable
        Response response =
                given()
                        .when()
                        .get("/comments?postId=1")
                        .then()
                        .extract()
                        .response();
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz",
                "Lew@alysha.tv", "Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
        //new String[0]:Not null String
    }
    @Test
    public void hasItemsResponseValidation() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        //send Get request and store the response in a varriable
        Response response =
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .extract()
                        .response();
        assertThat(response.jsonPath().getList("title"),
                hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        "qui est esse"));
    }
    @Test
    public void validateResponseHeader() {
        // Set the base URI for the RestAssured requests
        RestAssured.baseURI = "https://reqres.in";
        // Send a GET request to the specified endpoint ("/api/users?page=2")
        Response response = given()
                .when()
                .get("/api/users?page=2");  // Add a forward slash before "api"
        // Extract the response for further inspection
        Headers headers = response.getHeaders();
        // Iterate through the headers and validate the "Server" header value
        for (Header h : headers) {
            // Check if the current header is the "Server" header
            if (h.getName().equals("Server")) {
                // Uncomment the next line if you want to print the header name and value
                // System.out.println(h.getName() + ": " + h.getValue());
                // Validate that the "Server" header value is "cloudflare"
                Assert.assertEquals(h.getValue(), "cloudflare");
                // Print a success message if validation passes
                System.out.println("Validated Response From Header Successfully");
            }
        }
    }






}
