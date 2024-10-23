package balanca.interfaces;

import balanca.exceptions.ArquivoNaoEncontradoException;
import balanca.exceptions.ArquivoNaoEscritoException;

import java.io.FileNotFoundException;
import java.util.List;

public interface IBalanca<T> {
    void exportar(List<T> produtos, String pastaArquivoTxt) throws ArquivoNaoEscritoException, FileNotFoundException;
}
