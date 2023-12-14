package hillel.lesson29_awaitility_gson;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GsonTest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test
    public void checkNewBookingCreationGson() {
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        String jsonString = "{\n" +
                "    \"firstname\": \""+firstname+"\",\n" +
                "    \"lastname\": \""+lastname+"\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        String requestBody = JsonParser.parseString(jsonString).getAsJsonObject().toString();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/booking");

        JsonObject responseObject = JsonParser.parseString(response.asString()).getAsJsonObject();

        String actualFirstName = responseObject.get("booking").getAsJsonObject().get("firstname").getAsString();
        assertEquals(actualFirstName, firstname);

        String actualLastName = responseObject.get("booking").getAsJsonObject().get("lastname").getAsString();
        assertEquals(actualLastName, lastname);
    }
}
