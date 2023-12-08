package hillel.api.services;

import hillel.api.dto.BookingDTO;
import io.restassured.response.Response;

public class BookingService extends BaseService {
    protected static final String bookingPath = "/booking";

    public Response getBookingIdsList() {
        return setRequestSpec().when()
                .get(bookingPath);
    }

    public Response createNewBooking(BookingDTO booking) {
        return setRequestSpec()
                .body(booking)
                .when().post(bookingPath);
    }
}
