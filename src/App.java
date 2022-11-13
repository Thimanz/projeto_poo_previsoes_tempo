import java.io.FileInputStream;

import java.util.Properties;

import service.PrevisaoService;

public class App {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        properties.load(new FileInputStream("src/App.properties"));
        final String WHEATER_MAP_BASEURL= properties.getProperty("WHEATER_MAP_BASEURL");

        final String WHEATER_MAP_APPID= properties.getProperty("WHEATER_MAP_APPID");

        PrevisaoService service = new PrevisaoService();
        service.obterPrevisoesWheaterMap(WHEATER_MAP_BASEURL, WHEATER_MAP_APPID, "itu");
    }
}
