package service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Previsao;

public class PrevisaoService {
    private HttpClient client = HttpClient.newBuilder().build();
    public void obterPrevisoesWheaterMap(
    String url,
    String appid,
    String cidade
    )throws Exception{
        url = String.format("%s?q=%s&appid=%s", url, cidade, appid);

        //1. Construir um objeto que representa a requisicão
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();

        //2. enviar a requisicao ao servidor weatherMap
        var res = client.send(req, BodyHandlers.ofString());
        System.out.println(res.body());

        JSONObject raiz = new JSONObject(res.body());
        JSONArray list = raiz.getJSONArray("list");
        for (int i = 0; i< list.length(); i++){
            JSONObject previsaoJSON = list.getJSONObject(i);
            JSONObject main = previsaoJSON.getJSONObject("main");
            double temp_min = main.getDouble("temp_min");
            double temp_max = main.getDouble("temp_max");
            String dt_txt = previsaoJSON.getString("dt_txr");
            Previsao p = new Previsao(0, temp_min, temp_max, cidade, dt_txt);
            }
    }
}
