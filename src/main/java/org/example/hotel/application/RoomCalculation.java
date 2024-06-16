package org.example.hotel.application;

import java.math.BigDecimal;

public record RoomCalculation(
        int usedPremiumRooms,
        BigDecimal premiumIncome,
        int userEconomyRooms,
        BigDecimal economyIncome
) {
}
