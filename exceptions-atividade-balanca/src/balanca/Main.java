package balanca;

import balanca.enums.TipoBalanca;
import balanca.exceptions.ArquivoNaoEncontradoException;
import balanca.exceptions.ArquivoNaoEscritoException;
import balanca.factory.BalancaFactory;
import balanca.interfaces.IBalanca;
import balanca.models.Produto;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ArquivoNaoEscritoException, FileNotFoundException {
        Produto produto1 = new Produto();
        produto1.setCodigo(276);
        produto1.setDescricao("QUEIJO GRUYERE KG");
        produto1.setTipo("9");
        produto1.setValor(22.1);

        Produto produto2 = new Produto();
        produto2.setCodigo(288);
        produto2.setDescricao("QUEIJO PROVOLETE KG");
        produto2.setTipo("9");
        produto2.setValor(12.29);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);

        //IBalanca balancaFilizola = BalancaFactory.getBalanca(TipoBalanca.FINIZOLA_SMART);
        //balancaFilizola.exportar(produtos, "src/balanca/teste.txt");


        //IBalanca balancaToledo = BalancaFactory.getBalanca(TipoBalanca.TOLEDO_MGV6);
        //balancaToledo.exportar(produtos, "src/balanca/teste.txt");

        //IBalanca balancaUrano = BalancaFactory.getBalanca(TipoBalanca.URANO_INTEGRA);
        //balancaUrano.exportar(produtos, "src/balanca/teste.txt");

        System.out.println("Arquivos gerados com sucesso!");


    }
}

