package imposto;

import model.Item;
import model.Produto;

public class ICMS implements Imposto{
    @Override
    public String calcular(Item item) {
        if (item instanceof Produto) {
            double icms = 0.18;
            double imposto = item.getValor() * icms;
            item.setImpostoCalculado(imposto);
            item.setTotal(item.getValor() + imposto);
            return item.toString();
        } else {
            return "ICMS não aplicável para serviços.";
        }
    }
}
