package hillel.lesson28_api;

import hillel.api.dto.BookingDTO;
import hillel.api.dto.BookingIdDTO;
import hillel.api.dto.BookingResponseDTO;
import hillel.api.services.BookingService;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookingTest {
    BookingService bookingService = new BookingService();

    @Test
    public void checkBookingIdsV1() {
        Response response = bookingService.getBookingIdsList();

        response.then()
                .statusCode(200)
                .body("bookingid", hasItem(1));

        String responseString = response.then().extract().asString();
        assertTrue(responseString.contains("12"));
    }

    @Test
    public void checkBookingIdsV2() {
        BookingIdDTO expectedId1 = new BookingIdDTO(1);
        BookingIdDTO expectedId2 = new BookingIdDTO(12);
        Response response = bookingService.getBookingIdsList();

        List<BookingIdDTO> idList = response.then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList("", BookingIdDTO.class);

        MatcherAssert.assertThat(idList, allOf(hasItem(expectedId1), hasItem(expectedId2)));
    }

    @Test
    public void checkNewBookingCreation() {
        BookingDTO expectedBooking = TestData.buildDefaultBookingData(false);

        Response response = bookingService.createNewBooking(expectedBooking);

        BookingResponseDTO actualResponseDto = response.then().statusCode(200)
                .extract().as(BookingResponseDTO.class);

        assertEquals(actualResponseDto.getBooking().getTotalPrice(), expectedBooking.getTotalPrice());
    }
}
