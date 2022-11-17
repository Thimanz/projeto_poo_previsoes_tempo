package service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Previsao;

public class PrevisaoService {
    private HttpClient client = HttpClient.newBuilder().build();

    public void armazenarPrevisaoNoHistoricoOraclaCloud(Previsao p) throws Exception{
        JSONObject pJSON = new JSONObject();
        pJSON.put("cidade", p.getCidade());
        HttpRequest req = HttpRequest.newBuilder().POST(BodyPublishers.ofString(pJSON.toString()))
        .header("Content-Type", "application/json")
        .uri(URI.create("url oracle")).build();
    }

    public List <Previsao> obterPrevisoesWheaterMap(String url, String appid, String units, String cnt, String cidade) throws Exception{
        url = String.format("%s?q=%s&cnt=%s&appid=%s&units=%s", url, cidade, cnt, appid, units);
        List <Previsao> previsoes = new ArrayList<>();

        //1. Construir um objeto que representa a requisicão
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();

        //2. enviar a requisicao ao servidor weatherMap
        var res = client.send(req, BodyHandlers.ofString());

        JSONObject raiz = new JSONObject(res.body());
        JSONArray list = raiz.getJSONArray("list");
        for (int i = 0; i< list.length(); i++){
            JSONObject previsaoJSON = list.getJSONObject(i);
            JSONObject main = previsaoJSON.getJSONObject("main");
            double temp_min = main.getDouble("temp_min");
            double temp_max = main.getDouble("temp_max");
            String dt_txt = previsaoJSON.getString("dt_txt");
            Previsao p = new Previsao(0, temp_min, temp_max, cidade, dt_txt);
            previsoes.add(p);
        }
        return previsoes;
    }
}
