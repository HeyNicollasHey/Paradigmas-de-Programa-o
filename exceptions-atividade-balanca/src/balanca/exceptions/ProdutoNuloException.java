package balanca.exceptions;

public class ProdutoNuloException extends NullPointerException{

    public ProdutoNuloException(){
        super("O produto não pode ser nulo.");
    }
}
