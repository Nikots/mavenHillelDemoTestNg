package hillel.lesson28_api;

import com.github.javafaker.Faker;
import hillel.api.dto.BookingDTO;
import hillel.api.dto.BookingDatesDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestData {

    public static BookingDTO buildDefaultBookingData(boolean depositPaid) {
        Faker faker = new Faker();
        return BookingDTO.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .depositPaid(depositPaid)
                .totalPrice(new Random().nextInt(500, 9999))
                .bookingDates(
                        new BookingDatesDTO(setDate(60), setDate(70))
                )
                .additionalNeeds(faker.food().dish())
                .build();
    }

    public static String setDate(int shift) {
        LocalDate date = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
