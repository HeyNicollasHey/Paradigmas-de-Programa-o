import factory.ImpostoFactory;
import factory.TipoImposto;
import imposto.ICMS;
import imposto.IPI;
import imposto.Imposto;
import model.Produto;
import model.Servico;

public class Main {
    public static void main(String[] args) {

        Produto produto = new Produto(1,"Coca cola",10.0,false);

        Servico servico = new Servico(2,"Viagem",100);

        Imposto impostoFactory = ImpostoFactory.imposto(TipoImposto.TIPO_IPI);
        Imposto impostoFactory2 = ImpostoFactory.imposto(TipoImposto.TIPO_PIS);
        Imposto impostoFactory3 = ImpostoFactory.imposto(TipoImposto.TIPO_ICMS);
        Imposto impostoFactory4 = ImpostoFactory.imposto(TipoImposto.TIPO_ISS);

        System.out.println(impostoFactory.calcular(produto));
        System.out.println(impostoFactory2.calcular(produto));
        System.out.println(impostoFactory3.calcular(produto));
        System.out.println(impostoFactory4.calcular(servico));
    }
}