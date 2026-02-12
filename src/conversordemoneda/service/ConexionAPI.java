package conversordemoneda.service;

import conversordemoneda.modelos.json;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAPI {

    private final String API_KEY = ""; //<-- Coloca la clave de tu API aquí
    
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public double obtenerTasa(String moneda1, String moneda2) throws Exception {

        if (API_KEY == null) {
            throw new RuntimeException("API_KEY no valida o configurada");
        }

        String direccion = "https://v6.exchangerate-api.com/v6/"
                + API_KEY + "/pair/" + moneda1 + "/" + moneda2;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        json datos =
                gson.fromJson(response.body(), json.class);

        if (!"success".equals(datos.result())) {
            throw new RuntimeException("Error al obtener tasa de cambio");
        }

        return datos.conversion_rate();
    }
}
