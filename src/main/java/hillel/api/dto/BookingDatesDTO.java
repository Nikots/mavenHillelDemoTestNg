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
public class BookingDatesDTO {
    @JsonProperty("checkin") String checkIn;
    @JsonProperty("checkout") String checkOut;
}
