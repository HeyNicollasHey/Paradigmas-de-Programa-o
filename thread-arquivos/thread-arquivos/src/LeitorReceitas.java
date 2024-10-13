import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeitorReceitas {
    String caminhoArquivo = "src\\main\\java\\ThreadsFinanceiro\\dataBase\\receitas.csv";
    String linhaAtual = "";
    HashMap<String, Double> mapaReceitas = new HashMap<>();
    boolean ignorarPrimeiraLinha = true;
    double totalReceitas = 0;

    public double obterTotalReceitas() {
        return this.totalReceitas;
    }

    public String lerReceitas() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((linhaAtual = br.readLine()) != null) {
                if (ignorarPrimeiraLinha) {
                    ignorarPrimeiraLinha = false;
                    continue;
                }
                String data = String.valueOf(linhaAtual.split(",")[0].split(" ")[0]);
                double valorReceitas = Double.parseDouble(linhaAtual.split(",")[1].replace("\"", "").trim());
                totalReceitas += valorReceitas;

                mapaReceitas.merge(data, valorReceitas, Double::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Receitas: " + mapaReceitas + " \nTotal das Receitas: " + totalReceitas;
    }
}
