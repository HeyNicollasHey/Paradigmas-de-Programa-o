package balanca.services;

import balanca.exceptions.*;
import balanca.interfaces.IBalanca;
import balanca.models.Produto;

import java.io.*;
import java.util.List;

public class UranoIntegra implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt)
            throws ArquivoNaoEscritoException, ArquivoNaoEncontradoException {

        File directory = new File(pastaArquivoTxt);
        if (!directory.exists()) {
            throw new ArquivoNaoEncontradoException("O diretório especificado não existe: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt))) {

            for (Produto produto : produtos) {
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            throw new ArquivoNaoEncontradoException("Erro: arquivo não encontrado - " + e.getMessage());
        } catch (IOException e) {
            throw new ArquivoNaoEscritoException("Erro ao tentar escrever no arquivo: " + e.getMessage());
        } catch (FormatoInvalidoException | ProdutoNuloException | CodigoInvalidoException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatarProduto(Produto produto)
            throws FormatoInvalidoException, ProdutoNuloException, CodigoInvalidoException {

        try {
            if (produto == null) {
                throw new ProdutoNuloException();
            }

            String codigo = String.format("%06d", produto.getCodigo());
            if (produto.getCodigo() < 0) {
                throw new CodigoInvalidoException();
            }

            String flag = "*";
            String tipo = "9".equals(produto.getTipo()) ? "0" : "6";

            String descricao = produto.getDescricao();
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new ParametroNuloException("A descrição do produto não pode ser nula ou vazia.");
            }
            descricao = String.format("%-20s", descricao);

            String preco = String.format("%09.2f", produto.getValor()).replace(".", ",");
            if (produto.getValor() < 0) {
                throw new FormatoInvalidoException("O valor do produto é inválido: " + produto.getValor());
            }

            return codigo + flag + tipo + descricao + preco + "00000D";

        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoException("Erro ao formatar o produto: " + e.getMessage());
        }
    }
}
