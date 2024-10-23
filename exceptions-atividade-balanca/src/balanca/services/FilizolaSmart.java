package balanca.services;

import balanca.exceptions.*;
import balanca.interfaces.IBalanca;
import balanca.models.Produto;

import java.io.*;
import java.util.List;


public class FilizolaSmart implements IBalanca<Produto> {
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

        } catch (FileNotFoundException e){
            throw new ArquivoNaoEncontradoException("Erro: arquivo nao encontrado - " + e.getMessage());
        } catch (IOException e) {
            throw new ArquivoNaoEscritoException("Erro ao tentar escrever no arquivo: " + e.getMessage());
        } catch (FormatoInvalidoException e) {
            throw new RuntimeException(e);
        } catch (ProdutoNuloException e) {
            throw new RuntimeException(e);
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

            String tipo = "9".equals(produto.getTipo()) ? "P" : "U";

            String descricao = String.format("%-22s", produto.getDescricao());

            if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
                throw new ParametroNuloException("A descrição do produto não pode ser nula ou vazia.");
            }

            String preco = String.format("%07d", (int) (produto.getValor() * 100));

            if (produto.getValor() < 0) {
                throw new FormatoInvalidoException("O valor do produto é inválido: " + produto.getValor());
            }

            return codigo + tipo + descricao + preco + "000";

        } catch (IllegalArgumentException e) {
            throw new FormatoInvalidoException("Erro ao formatar o produto: " + e.getMessage());
        }
    }
}

