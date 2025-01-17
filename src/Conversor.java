import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Conversor {
    private consultaDivisa apiEchangeRate;
    private List<JsonObject> conversiones;

    public Conversor (){
        this.apiEchangeRate = new consultaDivisa();
        this.conversiones = new ArrayList<>();
    }

    public double convertirMoneda(String moneda, String monedaConvertida, double monto){
        try{
            double tasa = apiEchangeRate.obtenerCambioDivisas(moneda, monedaConvertida);
            double montoConvertido= monto * tasa;

            JsonObject conversion = new JsonObject();
            conversion.addProperty("origen", moneda);
            conversion.addProperty("destino", monedaConvertida);
            conversion.addProperty("monto", monto);
            conversion.addProperty("montoConvertido", montoConvertido);
            conversiones.add(conversion);

            return montoConvertido;
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return 0;
        }
    }

    public void almacenar(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();
        for (JsonObject conversion : conversiones){
            jsonArray.add(conversion);
        }

        try (FileWriter file = new FileWriter("conversiones.json")){
            file.write(gson.toJson(jsonArray));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarResumenConversiones() {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get("conversiones.json")));
            JsonArray conversionesArray = JsonParser.parseString(contenido).getAsJsonArray();
            for (int i = 0; i < conversionesArray.size(); i++) {
                JsonObject conversion = conversionesArray.get(i).getAsJsonObject();
                System.out.println(conversion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
