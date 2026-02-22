package com.alura.conversor.service;

import com.alura.conversor.model.RegistroConversion;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona el historial de conversiones realizadas
 * durante la sesión actual del programa.
 * Usa ArrayList para almacenar los registros en memoria.
 */
public class HistorialService {

    private final List<RegistroConversion> historial;

    public HistorialService() {
        this.historial = new ArrayList<>();
    }

    /**
     * Agrega una nueva conversión al historial.
     *
     * @param registro El registro de conversión a guardar.
     */
    public void guardar(RegistroConversion registro) {
        historial.add(registro);
        System.out.println("   💾 Conversión guardada en historial.");
    }

    /**
     * Muestra en consola todas las conversiones realizadas en la sesión.
     */
    public void mostrarHistorial() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              📋 HISTORIAL DE CONVERSIONES                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        if (historial.isEmpty()) {
            System.out.println("\n  ℹ️  Aún no se han realizado conversiones en esta sesión.");
        } else {
            System.out.printf("  Total de conversiones: %d%n%n", historial.size());
            for (int i = 0; i < historial.size(); i++) {
                System.out.printf("  %2d. %s%n", i + 1, historial.get(i));
            }
        }

        System.out.println("════════════════════════════════════════════════════════════");
    }

    /**
     * Retorna la lista completa del historial.
     *
     * @return Lista de registros de conversión.
     */
    public List<RegistroConversion> getHistorial() {
        return new ArrayList<>(historial); // copia defensiva
    }

    /**
     * Retorna la cantidad de conversiones realizadas.
     *
     * @return Número de conversiones.
     */
    public int getTotalConversiones() {
        return historial.size();
    }
}
