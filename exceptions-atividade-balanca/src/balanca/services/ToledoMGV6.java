package balanca.services;

import balanca.exceptions.*;
import balanca.interfaces.IBalanca;
import balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToledoMGV6 implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt)
            throws ArquivoNaoEscritoException, ArquivoNaoEncontradoException {

        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new ArquivoNaoEncontradoException("O diretório especificado não existe: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt))) {
            for (Produto produto : produtos) {
                try {
                    String linha = formatarProduto(produto);
                    writer.write(linha);
                    writer.newLine();
                } catch (FormatoInvalidoException | ProdutoNuloException e) {
                    System.err.println("Erro ao formatar o produto: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new ArquivoNaoEscritoException("Erro ao tentar escrever no arquivo: " + e.getMessage());
        }
    }

    private String formatarProduto(Produto produto) throws FormatoInvalidoException, ProdutoNuloException {
        try {
            if (produto == null) {
                throw new ProdutoNuloException();
            }

            String codigo = String.format("%06d", produto.getCodigo());
            if (produto.getCodigo() < 0) {
                throw new CodigoInvalidoException();
            }

            String preco = String.format("%06d", (int) (produto.getValor() * 100));
            if (produto.getValor() < 0) {
                throw new FormatoInvalidoException("O valor do produto é inválido: " + produto.getValor());
            }

            String descricao = String.format("%-50s", produto.getDescricao());
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new ParametroNuloException("A descrição do produto não pode ser nula ou vazia.");
            }

            String dept = "01";
            String tipo = "0";

            return dept + tipo + codigo + preco + "000" + descricao +
                    "0000000000|01|                                                                      0000000000000000000000000|0000|0||";

        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoException("Erro ao formatar o produto: " + e.getMessage());
        }
    }
}
