package models;

public class Produto {

    private String descricao;
    private double precoLata;
    private double precoCaixa;
    private String codigoDeBarras;

    public Produto(String descricao, String codigoDeBarras, double precoCaixa, double precoLata) {
        if (descricao.length() > 22) {
            throw new IllegalArgumentException("A descrição do produto não pode ter mais que 22 caracteres.");
        }
        this.descricao = descricao;
        this.codigoDeBarras = codigoDeBarras;
        this.precoCaixa = precoCaixa;
        this.precoLata = precoLata;
    }

    public void geradorEtiqueta(Produto produto) {
        System.out.println("^XA\n" +
                "^FO220,50^A0N,60,60^FDBar da Lora, Inc.^FS" +
                "^CFO,30\n" +
                "^FO220,155^FD Rua Patati e Patata N 666^FS\n" +
                "^FO220,195^FD Codo - MA^FS\n" +
                "^FO50,250^GB700,3,3^FS\n" +
                "\n" +
                "^FX" +
                "^CFA,30\n" +
                "^FO50,300^FD^FS\n" +
                "^FO50,340^FD" + this.descricao + "^FS\n" +
                "^FO50,380^FD R$" + this.precoLata + "^FS\n" +
                "^FO50,420^FD R$" + this.precoCaixa + "^FS\n" +
                "^CFA,15\n" +
                "\n" +
                "^FX Terceira seção com o código de barras.\n" +
                "^BY5,2,270\n" +
                "^FO100,550^BCN,270,Y,N,N" +
                "^FD" + this.codigoDeBarras + "^FS" +
                "^XZ");
    }
}
