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

        String[] opcoes = {"Pesquisar", "Histórico", "Sair"};
        var menu = JOptionPane.showOptionDialog(null, "Escolha o que deseja fazer", "Menu", 0, 3, null, opcoes, opcoes[0]);
        if (menu == 0)
        {
            String cidade = JOptionPane.showInputDialog("Digite uma cidade para saber suas previsoes");
            cidade = cidade.replaceAll("[^a-zA-Z]", "");
            PrevisaoService service = new PrevisaoService();
            List <Previsao> previsoes = service.obterPrevisoesWheaterMap(WHEATER_MAP_BASEURL, WHEATER_MAP_APPID, cidade);
            String mensagem = "";
            for (int i = 0; i < previsoes.size(); i++){
                mensagem = mensagem.concat(previsoes.get(i).toString());
            }
            JOptionPane.showMessageDialog(null, mensagem, "Previsoes de 3 em 3 horas", 1);
        }
    }
}
