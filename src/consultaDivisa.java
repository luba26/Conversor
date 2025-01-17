import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.google.gson.JsonParser.*;


public class consultaDivisa {

    private static final String API_KEY = "458534610f7adfe07ecd0817";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public double obtenerCambioDivisas(String moneda, String monedaConvertida) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        String url = BASE_URL + API_KEY + "/latest/" + moneda;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();
        JsonObject jsonObject= JsonParser.parseString(jsonResponse).getAsJsonObject();

        return jsonObject.getAsJsonObject("conversion_rates").get(monedaConvertida).getAsDouble();
    }

}








