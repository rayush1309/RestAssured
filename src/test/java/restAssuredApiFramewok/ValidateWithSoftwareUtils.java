package restAssuredApiFramewok;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonArrayReader;
import utils.PropertyReader;
import utils.SoftAssertionUtils;

import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class ValidateWithSoftwareUtils extends JsonArrayReader{
    SoftAssertionUtils st= new SoftAssertionUtils();


    @Test
    public void validateResponseUsingSoftUtils(){
        String url= PropertyReader.propertyReader("enviornmentdetails.properties","URI");
        RestAssured.baseURI=url;
        System.out.println(url);
        Response response=
                given()
                        .queryParam("page",2)
                        .when()
                        .get("/api/users/2")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
          st.assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code,"Status code is not 200");
          st.assertAll();


        System.out.println("validateResponseUsingSoftUtils Executed Sucessfully");



    }
    @Test
    public void TestJsonArray() throws IOException, ParseException{
        Object ans= JsonArrayReader.getJsonArrayData("languages",0);
        System.out.println(ans);
    }
    @Test
    public void Test() throws IOException,ParseException{
        JsonArrayReader.getJsonArrayData("technology",2);
        JSONArray jsonArray= getJsonArray("contact");
        Iterator<String> It=jsonArray.iterator();
        while(It.hasNext()){
            System.out.println(It.next());


        }

    }

}
