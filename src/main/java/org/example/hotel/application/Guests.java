package org.example.hotel.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Guests {

    // example: guests: 22, 99.99, 100, 101

    // premium growing: 0, 101, 201
    // economy growing: 0, 99.00, 121,99

    private final List<BigDecimal> premiumGrowing;

    private final List<BigDecimal> economyGrowing;


    public Guests(Collection<BigDecimal> guestPrices, BigDecimal separator) {

        // 1. separate premium/economy
        var separation = guestPrices.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.partitioningBy(price -> price.compareTo(separator) >= 0));
        List<BigDecimal> premium = separation.get(true);
        List<BigDecimal> economy = separation.get(false);

        // 2. fill growing
        premiumGrowing = new ArrayList<>(economy.size() + 1);
        premiumGrowing.add(BigDecimal.ZERO);

        BigDecimal accumulator = BigDecimal.ZERO;
        for (BigDecimal premiumPrice : premium) {
            accumulator = accumulator.add(premiumPrice);
            premiumGrowing.add(accumulator);
        }
        economyGrowing = new ArrayList<>(economy.size() + 1);
        economyGrowing.add(BigDecimal.ZERO);
        accumulator = BigDecimal.ZERO;
        for (BigDecimal economyPrice : economy) {
            accumulator = accumulator.add(economyPrice);
            economyGrowing.add(accumulator);
        }
    }

    public int premiumGuestsNumber() {
        return premiumGrowing.size() - 1;
    }

    public int economyGuestsNumber() {
        return economyGrowing.size() - 1;
    }

    /**
     * Income from first N premium guests
     */
    public BigDecimal premiumIncome(int guestNumber) {
        return premiumGrowing.get(guestNumber);
    }

    /**
     * Income from first N economy guests
     */
    public BigDecimal economyIncome(int guestNumber) {
        return economyGrowing.get(guestNumber);
    }

    /**
     * Income from N economy guests starting from given index
     *
     * @param guestNumber  number of guests
     * @param startingFrom starting index
     * @return income
     */
    public BigDecimal economyIncome(int guestNumber, int startingFrom) {
        return economyIncome(guestNumber + startingFrom).subtract(economyIncome(startingFrom));
    }
}