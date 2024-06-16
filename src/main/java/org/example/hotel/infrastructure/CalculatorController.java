package org.example.hotel.infrastructure;

import jakarta.validation.Valid;
import org.example.hotel.application.CalculatorService;
import org.example.hotel.domain.RoomCalculation;
import org.example.hotel.domain.RoomQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CalculatorController {

    private final CalculatorService calculatorService;

    CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    RoomCalculation calculate(@Valid RoomQuery roomQuery) {
        return calculatorService.calculate(roomQuery);
    }
}
