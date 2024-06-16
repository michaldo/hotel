package org.example.hotel.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class GuestsTest {

    @Test
    void test() {
        Guests guests = new Guests(
                List.of(
                        new BigDecimal("22"),
                        new BigDecimal("99.99"),
                        new BigDecimal("100"),
                        new BigDecimal("101")),
                new BigDecimal(100));

        assertThat(guests.premiumGuestsNumber()).isEqualTo(2);
        assertThat(guests.economyGuestsNumber()).isEqualTo(2);

        assertThat(guests.premiumIncome(0)).isEqualTo(ZERO);
        assertThat(guests.premiumIncome(1)).isEqualByComparingTo("101");
        assertThat(guests.premiumIncome(2)).isEqualByComparingTo("201");

        assertThat(guests.economyIncome(0)).isEqualTo(ZERO);
        assertThat(guests.economyIncome(1)).isEqualByComparingTo("99.99");
        assertThat(guests.economyIncome(2)).isEqualByComparingTo("121.99");
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            # number of guests,  starting from, expected income
            0, 0, 0
            1, 0, 99.99
            1, 1, 45
            1, 2, 23
            2, 0, 144.99
            2, 2, 45
            """)
    void test_economy_income_starting_from(int numberOfGuests, int startingFrom, BigDecimal expectedIncome) {

        Guests guests = new Guests(List.of(
                new BigDecimal(23),
                new BigDecimal(45),
                new BigDecimal(22),
                new BigDecimal("99.99")),

                new BigDecimal(100)
        );

        assertThat(guests.economyIncome(numberOfGuests, startingFrom)).isEqualByComparingTo(expectedIncome);
    }
}