package com.alura.conversor;

import com.alura.conversor.model.RegistroConversion;
import com.alura.conversor.service.ApiService;
import com.alura.conversor.service.HistorialService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiService apiService = new ApiService();
        HistorialService historialService = new HistorialService();
        int opcion;

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      CONVERSOR DE MONEDAS - Alura      ║");
        System.out.println("║         Challenge ONE | Oracle          ║");
        System.out.println("╚════════════════════════════════════════╝");

        do {
            mostrarMenu();
            System.out.print("\n👉 Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Por favor ingresa un número válido.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1 -> realizarConversion("USD", "COP", scanner, apiService, historialService);
                case 2 -> realizarConversion("COP", "USD", scanner, apiService, historialService);
                case 3 -> realizarConversion("USD", "BRL", scanner, apiService, historialService);
                case 4 -> realizarConversion("BRL", "USD", scanner, apiService, historialService);
                case 5 -> realizarConversion("USD", "EUR", scanner, apiService, historialService);
                case 6 -> realizarConversion("EUR", "USD", scanner, apiService, historialService);
                case 7 -> realizarConversion("USD", "ARS", scanner, apiService, historialService);
                case 8 -> realizarConversion("ARS", "USD", scanner, apiService, historialService);
                case 9 -> conversionPersonalizada(scanner, apiService, historialService);
                case 10 -> historialService.mostrarHistorial();
                case 0 -> System.out.println("\n👋 ¡Gracias por usar el Conversor de Monedas! Hasta luego.");
                default -> System.out.println("⚠️  Opción no válida. Elige entre 0 y 10.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  💱 CONVERSIONES RÁPIDAS:");
        System.out.println("──────────────────────────────────────────");
        System.out.println("  1.  USD  →  COP  (Peso Colombiano)");
        System.out.println("  2.  COP  →  USD  (Dólar Americano)");
        System.out.println("  3.  USD  →  BRL  (Real Brasileño)");
        System.out.println("  4.  BRL  →  USD  (Dólar Americano)");
        System.out.println("  5.  USD  →  EUR  (Euro)");
        System.out.println("  6.  EUR  →  USD  (Dólar Americano)");
        System.out.println("  7.  USD  →  ARS  (Peso Argentino)");
        System.out.println("  8.  ARS  →  USD  (Dólar Americano)");
        System.out.println("──────────────────────────────────────────");
        System.out.println("  9.  🔁  Conversión personalizada");
        System.out.println("  10. 📋  Ver historial de conversiones");
        System.out.println("  0.  🚪  Salir");
        System.out.println("══════════════════════════════════════════");
    }

    private static void realizarConversion(String origen, String destino,
                                           Scanner scanner, ApiService apiService,
                                           HistorialService historialService) {
        System.out.printf("%n💰 Ingresa la cantidad en %s: ", origen);
        try {
            double cantidad = Double.parseDouble(scanner.nextLine().trim());

            if (cantidad <= 0) {
                System.out.println("⚠️  La cantidad debe ser mayor a 0.");
                return;
            }

            System.out.println("⏳ Consultando tasas de cambio...");
            double tasa = apiService.obtenerTasa(origen, destino);
            double resultado = cantidad * tasa;

            System.out.printf("%n✅ %,.2f %s  =  %,.2f %s%n", cantidad, origen, resultado, destino);
            System.out.printf("   (Tasa de cambio: 1 %s = %,.4f %s)%n", origen, tasa, destino);

            historialService.guardar(new RegistroConversion(origen, destino, cantidad, resultado, tasa));

        } catch (NumberFormatException e) {
            System.out.println("❌ Cantidad inválida. Ingresa un número.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void conversionPersonalizada(Scanner scanner, ApiService apiService,
                                                HistorialService historialService) {
        System.out.println("\n🔁 CONVERSIÓN PERSONALIZADA");
        System.out.println("   (Usa códigos ISO 4217: USD, EUR, COP, BRL, ARS, GBP, JPY, etc.)");

        System.out.print("   Moneda de origen  : ");
        String origen = scanner.nextLine().toUpperCase().trim();

        System.out.print("   Moneda de destino : ");
        String destino = scanner.nextLine().toUpperCase().trim();

        System.out.printf("   Cantidad en %s   : ", origen);
        try {
            double cantidad = Double.parseDouble(scanner.nextLine().trim());

            if (cantidad <= 0) {
                System.out.println("⚠️  La cantidad debe ser mayor a 0.");
                return;
            }

            System.out.println("⏳ Consultando tasas de cambio...");
            double tasa = apiService.obtenerTasa(origen, destino);
            double resultado = cantidad * tasa;

            System.out.printf("%n✅ %,.2f %s  =  %,.2f %s%n", cantidad, origen, resultado, destino);
            System.out.printf("   (Tasa de cambio: 1 %s = %,.4f %s)%n", origen, tasa, destino);

            historialService.guardar(new RegistroConversion(origen, destino, cantidad, resultado, tasa));

        } catch (NumberFormatException e) {
            System.out.println("❌ Cantidad inválida. Ingresa un número.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
