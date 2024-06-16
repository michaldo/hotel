package org.example.hotel.application;

import org.example.hotel.domain.Calculator;
import org.example.hotel.domain.Guests;
import org.example.hotel.domain.RoomCalculation;
import org.example.hotel.domain.RoomQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    private final Guests guests;

    CalculatorService(GuestsProperties guestsProperties) {
        this.guests = new Guests(guestsProperties.guestPrice(), new BigDecimal(100));
    }

    public RoomCalculation calculate(RoomQuery roomQuery){
        return Calculator.calculate(roomQuery, guests);
    }
}
