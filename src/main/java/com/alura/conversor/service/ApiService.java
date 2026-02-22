package com.alura.conversor.service;

import com.alura.conversor.model.RespuestaPar;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Servicio responsable de consumir la ExchangeRate API
 * y retornar las tasas de cambio entre monedas.
 *
 * API usada: https://v6.exchangerate-api.com
 * Documentación: https://www.exchangerate-api.com/docs/overview
 */
public class ApiService {

    // 🔑 IMPORTANTE: Reemplaza esto con tu API Key de https://www.exchangerate-api.com
    private static final String API_KEY = "21cfff06e922c380ad9ddc80";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient httpClient;
    private final Gson gson;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    /**
     * Obtiene la tasa de cambio entre dos monedas consultando la API.
     *
     * @param monedaOrigen  Código ISO de la moneda base (ej: "USD")
     * @param monedaDestino Código ISO de la moneda destino (ej: "COP")
     * @return Tasa de cambio (cuántas unidades de destino equivalen a 1 de origen)
     * @throws IOException Si hay error de red o conexión
     * @throws InterruptedException Si la solicitud es interrumpida
     * @throws RuntimeException Si la API retorna error o la moneda no existe
     */
    public double obtenerTasa(String monedaOrigen, String monedaDestino)
            throws IOException, InterruptedException {

        validarCodigoMoneda(monedaOrigen);
        validarCodigoMoneda(monedaDestino);

        String url = BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                "Error al conectar con la API. Código HTTP: " + response.statusCode()
            );
        }

        RespuestaPar respuesta = gson.fromJson(response.body(), RespuestaPar.class);

        if (respuesta == null) {
            throw new RuntimeException("No se pudo procesar la respuesta de la API.");
        }

        if (!"success".equals(respuesta.result())) {
            throw new RuntimeException(
                "La API reportó un error. Verifica tu API Key y los códigos de moneda."
            );
        }

        return respuesta.conversion_rate();
    }

    /**
     * Valida que el código de moneda tenga el formato correcto (3 letras).
     */
    private void validarCodigoMoneda(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de moneda no puede estar vacío.");
        }
        if (codigo.length() != 3 || !codigo.matches("[A-Z]+")) {
            throw new IllegalArgumentException(
                "Código de moneda inválido: '" + codigo + "'. " +
                "Debe ser de 3 letras mayúsculas (ej: USD, EUR, COP)."
            );
        }
    }
}
