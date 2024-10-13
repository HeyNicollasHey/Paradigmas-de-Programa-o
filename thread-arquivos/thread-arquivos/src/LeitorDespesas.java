import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeitorDespesas {
    String caminhoArquivo = "src\\despesas.csv";
    String linhaAtual = "";
    HashMap<String, Double> mapaDespesas = new HashMap<>();
    boolean ignorarPrimeiraLinha = true;
    double totalDespesas = 0;

    public double obterTotalDespesas() {
        return this.totalDespesas;
    }

    public String lerDespesas() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((linhaAtual = br.readLine()) != null) {
                if (ignorarPrimeiraLinha) {
                    ignorarPrimeiraLinha = false;
                    continue;
                }
                String data = String.valueOf(linhaAtual.split(",")[0].split(" ")[0]);
                double valorDespesas = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalDespesas += valorDespesas;

                mapaDespesas.merge(data, valorDespesas, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Despesas: " + mapaDespesas + " \nTotal das Despesas: " + totalDespesas;
    }
}