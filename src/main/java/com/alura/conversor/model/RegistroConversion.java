package com.alura.conversor.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un registro individual del historial de conversiones.
 */
public class RegistroConversion {

    private final String monedaOrigen;
    private final String monedaDestino;
    private final double cantidadOrigen;
    private final double cantidadResultado;
    private final double tasaCambio;
    private final String fechaHora;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public RegistroConversion(String monedaOrigen, String monedaDestino,
                              double cantidadOrigen, double cantidadResultado,
                              double tasaCambio) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOrigen = cantidadOrigen;
        this.cantidadResultado = cantidadResultado;
        this.tasaCambio = tasaCambio;
        this.fechaHora = LocalDateTime.now().format(FORMATTER);
    }

    public String getMonedaOrigen() { return monedaOrigen; }
    public String getMonedaDestino() { return monedaDestino; }
    public double getCantidadOrigen() { return cantidadOrigen; }
    public double getCantidadResultado() { return cantidadResultado; }
    public double getTasaCambio() { return tasaCambio; }
    public String getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s → %.4f %s  (tasa: %.4f)",
                fechaHora, cantidadOrigen, monedaOrigen,
                cantidadResultado, monedaDestino, tasaCambio);
    }
}
