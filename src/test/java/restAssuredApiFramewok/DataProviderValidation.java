package userManagement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DataProviderValidation {

    @DataProvider(name = "testdata")
    public Object[][] testData() {
        return new Object[][]{
                {"1", "John"},
                {"2", "James Bond"},
                {"3", "Alex"},
                {"4", "Ayush"},
                {"5","Father of SO"}

        };
    }

    @Test(dataProvider = "testdata")
    @Parameters({"id", "name"})
    public void testEndPoint(String id, String name) {
        given()
                .queryParam("id", id)
                .queryParam("name", name)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }
}
