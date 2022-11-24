import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import model.Previsao;
import java.util.List;
import service.PrevisaoService;

public class App {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/App.properties"));
        final String WHEATER_MAP_BASEURL = properties.getProperty("WHEATER_MAP_BASEURL");
        final String WHEATER_MAP_APPID = properties.getProperty("WHEATER_MAP_APPID");
        final String WEATHER_MAP_UNITS = properties.getProperty("WEATHER_MAP_UNITS");
        final String WEATHER_MAP_CNT = properties.getProperty("WEATHER_MAP_CNT");

        final String URL_ORACLE = properties.getProperty("URL_ORACLE");

        PrevisaoService service = new PrevisaoService();

        String[] opcoes = { "Pesquisar", "Histórico", "Sair" };
        int menu;
        do {
            menu = JOptionPane.showOptionDialog(null, "Escolha o que deseja fazer", "Menu", 0, 3, null, opcoes,
                    opcoes[0]);
            if (menu == 0) {
                String cidade = JOptionPane.showInputDialog("Digite uma cidade para saber suas previsoes");
                cidade = cidade.replaceAll("\\s", "+").replaceAll("[^a-zA-Z+]", "");
                List<Previsao> previsoes = service.obterPrevisoesWheaterMap(WHEATER_MAP_BASEURL, WHEATER_MAP_APPID,
                        WEATHER_MAP_UNITS, WEATHER_MAP_CNT, cidade);
                service.armazenarPrevisaoNoHistoricoOraclaCloud(previsoes.get(0), URL_ORACLE);
                String mensagem = "";
                for (int i = 0; i < previsoes.size(); i++) {
                    mensagem += previsoes.get(i);
                }
                JOptionPane.showMessageDialog(null, mensagem, previsoes.get(0).getCidade(), 1);
            } else if (menu == 1) {
                JOptionPane.showMessageDialog(null, service.getPrevisoesOracleCloud(URL_ORACLE),
                        "Histórico de previsões", 1);
            }
        } while (menu != 2);
    }
}
