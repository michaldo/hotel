package org.example.hotel.application;

import java.math.BigDecimal;

public class Calculator {

    public static RoomCalculation calculate(RoomQuery roomQuery, Guests guests) {
        int usedPremiumRooms;
        int usedEconomyRooms;
        BigDecimal premiumIncome;
        BigDecimal economyIncome;

        // 1. premium guests
        int premiumRoomsForPremiumUsers = Math.min(
                roomQuery.premiumRoomsNumber(), guests.premiumGuestsNumber());
        usedPremiumRooms = premiumRoomsForPremiumUsers;
        premiumIncome = guests.premiumIncome(premiumRoomsForPremiumUsers);

        // 2. economy guest
        if (roomQuery.economyRoomsNumber() >= guests.economyGuestsNumber()) {
            // enough economy rooms for economy guest
            usedEconomyRooms = guests.economyGuestsNumber();
            economyIncome = guests.economyIncome(usedEconomyRooms);
        } else {
            // upgrade
            usedEconomyRooms = roomQuery.economyRoomsNumber();
            int remainingEconomyGuests = guests.economyGuestsNumber() - usedEconomyRooms;
            int remainingPremiumRooms = roomQuery.premiumRoomsNumber() - usedPremiumRooms;
            int premiumRoomsForEconomyUsers = Math.min(remainingEconomyGuests, remainingPremiumRooms);

            usedPremiumRooms += premiumRoomsForEconomyUsers;
            premiumIncome = premiumIncome.add(guests.economyIncome(premiumRoomsForEconomyUsers));
            economyIncome = guests.economyIncome(usedEconomyRooms, premiumRoomsForEconomyUsers);
        }
        return new RoomCalculation(
                usedPremiumRooms,
                premiumIncome,
                usedEconomyRooms,
                economyIncome);
    }
}
