package balanca.exceptions;

import java.io.IOException;

public class ArquivoNaoEscritoException extends IOException {

    public ArquivoNaoEscritoException(String e){
        super("Nao foi possivel escrever no arquivo");
    }
}