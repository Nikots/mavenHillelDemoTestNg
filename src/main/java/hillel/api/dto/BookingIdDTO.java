package hillel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookingIdDTO {
    @JsonProperty("bookingid") private int bookingId;

    // Конструктор за замовчуванням
    public BookingIdDTO() {
    }

    // Конструктор з параметрами
    public BookingIdDTO(int bookingId) {
        this.bookingId = bookingId;
    }

    // Гетер
    public int getBookingId() {
        return bookingId;
    }

    // Сетер
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // toString метод для відладки
    @Override
    public String toString() {
        return "BookingIdDto{" +
                "bookingid=" + bookingId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingIdDTO that = (BookingIdDTO) o;
        return bookingId == that.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
