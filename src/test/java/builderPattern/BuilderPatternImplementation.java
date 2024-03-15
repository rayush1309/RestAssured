package builderPattern;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BuilderPatternImplementation {
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpecification;

    private RequestSpecification getRequestSpecificationBuilder(String queryParam, String contentType) {
        // Corrected the variable name and removed the extra semicolon
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(contentType)
                .addQueryParam("paramName", queryParam)
                .build();

        return requestSpec;
    }
    private ResponseSpecification setResponseSpecificationBuilder(){
        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        return  setResponseSpecificationBuilder();


    }

    @Test
    public void testRestAssuredBuilderPattern() {
        requestSpec = getRequestSpecificationBuilder("1", "application/json");
        given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
