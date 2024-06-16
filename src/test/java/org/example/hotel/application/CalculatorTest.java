package org.example.hotel.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    private final BigDecimal separator = new BigDecimal(100);

    // [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
    private Guests guests = new Guests(List.of(
            new BigDecimal(23),
            new BigDecimal(45),
            new BigDecimal(155),
            new BigDecimal(374),
            new BigDecimal(22),
            new BigDecimal("99.99"),
            new BigDecimal(100),
            new BigDecimal(101),
            new BigDecimal(115),
            new BigDecimal(209)),

            separator
    );

    /*
    Test 1
        ● (input) Free Premium rooms: 3
        ● (input) Free Economy rooms: 3
        ● (output) Usage Premium: 3 (EUR 738)
        ● (output) Usage Economy: 3 (EUR 167.99)
    Test 2
        ● (input) Free Premium rooms: 7
        ● (input) Free Economy rooms: 5
        ● (output) Usage Premium: 6 (EUR 1054)
        ● (output) Usage Economy: 4 (EUR 189.99)
    Test 3
        ● (input) Free Premium rooms: 2
        ● (input) Free Economy rooms: 7
        ● (output) Usage Premium: 2 (EUR 583)
        ● (output) Usage Economy: 4 (EUR 189.99)
    Test 4
        ● (input) Free Premium rooms: 7
        ● (input) Free Economy rooms: 1
        ● (output) Usage Premium: 7 (EUR 1153)
        ● (output) Usage Economy: 1 (EUR 45.99)
     */

    @ParameterizedTest
    @CsvSource(textBlock = """
            # premium rooms, economy rooms, expected premium use+income, expected economy use+income
            3, 3, 3, 738, 3, 167.99
            7, 5, 6, 1054, 4, 189.99
            2, 7, 2, 583, 4, 189.99
            7, 1, 7, 1153.99, 1, 45.00
            """)
    void test(
            int premiumRoomsNumber, int economyRoomsNumber,
            int expectedUsedPremiumRooms, BigDecimal expectedPremiumIncome,
            int expectedUsedEconomyRooms, BigDecimal expectedEconomyIncome) {

        RoomCalculation utilization = Calculator.calculate(
                new RoomQuery(premiumRoomsNumber,economyRoomsNumber), guests);

        assertThat(utilization)
                .returns(expectedUsedPremiumRooms, RoomCalculation::usedPremiumRooms)
                .returns(expectedPremiumIncome, RoomCalculation::premiumIncome)
                .returns(expectedUsedEconomyRooms, RoomCalculation::userEconomyRooms)
                .returns(expectedEconomyIncome, RoomCalculation::economyIncome);
    }
}