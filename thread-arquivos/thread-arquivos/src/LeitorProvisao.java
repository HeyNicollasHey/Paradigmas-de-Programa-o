import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeitorProvisao {
    String caminhoArquivo = "src\\provisao.csv";
    String linhaAtual = "";
    HashMap<String, Double> mapaProvisao = new HashMap<>();
    boolean ignorarPrimeiraLinha = true;
    double totalProvisoes = 0;

    public String lerProvisao() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((linhaAtual = br.readLine()) != null) {
                if (ignorarPrimeiraLinha) {
                    ignorarPrimeiraLinha = false;
                    continue;
                }
                String data = String.valueOf(linhaAtual.split(",")[0].split(" ")[0]);
                double valorProvisao = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalProvisoes += valorProvisao;

                mapaProvisao.merge(data, valorProvisao, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Provisões: " + mapaProvisao + " \nTotal das Provisões: " + totalProvisoes;
    }
}
