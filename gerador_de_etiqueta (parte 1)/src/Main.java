import models.Produto;

public class Main {
    public static void main(String[] args) {

        Produto produto = new Produto("Coca Zero", "847543764", 50.0, 4);

        produto.geradorEtiqueta(produto);

    }
}