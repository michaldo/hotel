package org.example.hotel.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.List;

@ConfigurationProperties
public record GuestsProperties(
        List<BigDecimal> guestPrice) { }
