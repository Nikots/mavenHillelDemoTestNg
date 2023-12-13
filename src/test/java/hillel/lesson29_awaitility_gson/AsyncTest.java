package hillel.lesson29_awaitility_gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.RestAssured.given;

public class AsyncTest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testWithDelay() {

        given()
                .when()
                .get("/users?delay=15")
                .then()
                .statusCode(200);
    }

    @Test
    public void testWithAwaitility() {

        AtomicReference<Response> atomicResponse = null;

        Awaitility.await().atMost(15, TimeUnit.SECONDS)
                        .pollInterval(3, TimeUnit.SECONDS)
                                .until(() -> {
                                    Response response = given()
                                            .when()
                                            .get("/users?delay=45");
                                            if(response.getStatusCode() == 200
                                                    && response.getBody().asString().contains("george.bluth@reqres.in")) {
                                                atomicResponse.set(response);
                                                return true;
                                            }
                                    return null;
                                });

        Response resp = atomicResponse.get();
    }
}
