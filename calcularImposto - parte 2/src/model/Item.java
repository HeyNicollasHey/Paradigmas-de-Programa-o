package model;

public class Item {
    private int codigo;
    private String descricao;
    private double valor;
    private double impostoCalculado;
    private double total;


    public double getValor() {
        return valor;
    }

    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.impostoCalculado = 0;
        this.total = 0;
    }

    public void setImpostoCalculado(double impostoCalculado) {
        this.impostoCalculado = impostoCalculado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", impostoCalculado=" + impostoCalculado +
                ", total=" + total +
                '}';
    }
}
