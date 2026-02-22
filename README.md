# 💱 Conversor de Monedas

> Challenge ONE | Alura Latam + Oracle — Backend con Java

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue?logo=apachemaven)
![API](https://img.shields.io/badge/ExchangeRate--API-v6-green)
![Status](https://img.shields.io/badge/Status-Completado-brightgreen)

---

## 📌 Descripción

Aplicación de consola desarrollada en **Java 17** que permite convertir divisas en tiempo real consumiendo la **ExchangeRate API**. Proyecto realizado como parte del **Challenge ONE de Alura Latam y Oracle**.

---

## ✨ Funcionalidades

- ✅ Conversión entre 8 pares de monedas predefinidos
- ✅ Conversión personalizada entre cualquier par de divisas soportado
- ✅ Tasas de cambio en tiempo real vía API REST
- ✅ Historial de conversiones de la sesión actual
- ✅ Validación de entradas y manejo de errores
- ✅ Arquitectura en capas con OOP aplicado

---

## 💱 Monedas soportadas (menú rápido)

| Opción | Conversión |
|--------|-----------|
| 1 | USD → COP (Peso Colombiano) |
| 2 | COP → USD (Dólar Americano) |
| 3 | USD → BRL (Real Brasileño) |
| 4 | BRL → USD (Dólar Americano) |
| 5 | USD → EUR (Euro) |
| 6 | EUR → USD (Dólar Americano) |
| 7 | USD → ARS (Peso Argentino) |
| 8 | ARS → USD (Dólar Americano) |
| 9 | 🔁 Personalizada (cualquier código ISO 4217) |

---

## 🏗️ Estructura del Proyecto

```
conversor-monedas/
├── src/
│   └── main/
│       └── java/
│           └── com/alura/conversor/
│               ├── Main.java                  # Punto de entrada, menú principal
│               ├── model/
│               │   ├── RespuestaPar.java      # Record que mapea el JSON del endpoint /pair/
│               │   └── RegistroConversion.java # Modelo del historial
│               └── service/
│                   ├── ApiService.java        # Consumo de la ExchangeRate API
│                   └── HistorialService.java  # Gestión del historial en memoria
├── pom.xml                                    # Dependencias Maven (Gson)
└── README.md
```

---

## ⚙️ Configuración y Ejecución

### Pre-requisitos
- Java 17+
- Maven 3.8+
- Conexión a internet

### Pasos

1. **Clona o descarga el proyecto**

2. **Obtén tu API Key gratis en:**
   ```
   https://www.exchangerate-api.com
   ```

3. **Configura tu API Key en `ApiService.java`:**
   ```java
   private static final String API_KEY = "TU_API_KEY_AQUI";
   ```

4. **Compila y empaqueta:**
   ```bash
   mvn clean package
   ```

5. **Ejecuta:**
   ```bash
   java -jar target/conversor-monedas-1.0-SNAPSHOT.jar
   ```

---

## 🧰 Tecnologías utilizadas

| Tecnología | Uso |
|-----------|-----|
| Java 17 | Lenguaje principal |
| Java HttpClient | Consumo de API REST |
| Gson 2.10.1 | Parsing de respuesta JSON |
| Maven | Gestión de dependencias |
| Record (Java 16+) | Mapeo inmutable del JSON |

---

## 📡 API utilizada

**ExchangeRate API v6** — [https://www.exchangerate-api.com](https://www.exchangerate-api.com)

Endpoint usado:
```
GET https://v6.exchangerate-api.com/v6/{API_KEY}/pair/{MONEDA_ORIGEN}/{MONEDA_DESTINO}
```

Respuesta JSON:
```json
{
  "result": "success",
  "base_code": "USD",
  "target_code": "COP",
  "conversion_rate": 4150.5
}
```

---

## 👨‍💻 Autor

Desarrollado como parte del programa **ONE — Oracle Next Education** con **Alura Latam**.

#Oracle #AluraLatam #ONE #JavaChallenge
