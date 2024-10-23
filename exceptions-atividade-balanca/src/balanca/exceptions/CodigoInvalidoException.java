package balanca.exceptions;

public class CodigoInvalidoException extends IllegalArgumentException{
    public CodigoInvalidoException(){
        super("Codigo invalido");
    }
}
