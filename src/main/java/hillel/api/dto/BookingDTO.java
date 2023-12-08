package hillel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    @JsonProperty("firstname") String firstName;
    @JsonProperty("lastname") String lastName;
    @JsonProperty("totalprice") int totalPrice;
    @JsonProperty("depositpaid") boolean depositPaid;
    @JsonProperty("bookingdates") BookingDatesDTO bookingDates;
    @JsonProperty("additionalneeds") String additionalNeeds;

}
