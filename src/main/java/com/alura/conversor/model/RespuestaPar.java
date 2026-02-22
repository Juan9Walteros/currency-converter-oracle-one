package com.alura.conversor.model;

/**
 * Record que mapea la respuesta JSON del endpoint /pair/ de ExchangeRate API.
 * Ejemplo de respuesta:
 * {
 *   "result": "success",
 *   "base_code": "USD",
 *   "target_code": "COP",
 *   "conversion_rate": 4150.5
 * }
 */
public record RespuestaPar(
        String result,
        String base_code,
        String target_code,
        double conversion_rate
) {}
