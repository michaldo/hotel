package org.example.hotel.domain;
import jakarta.validation.constraints.Min;

public record RoomQuery(
        @Min(0) int premiumRoomsNumber,
        @Min(0) int economyRoomsNumber) {
}
