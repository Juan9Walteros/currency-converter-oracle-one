package com.alura.conversor.model;

import java.util.Map;

/**
 * Record que mapea la respuesta JSON de la ExchangeRate API.
 * Ejemplo de respuesta:
 * {
 *   "result": "success",
 *   "base_code": "USD",
 *   "conversion_rates": { "EUR": 0.93, "COP": 4150.5, ... }
 * }
 */
public record RespuestaApi(
        String result,
        String base_code,
        Map<String, Double> conversion_rates
) {}
