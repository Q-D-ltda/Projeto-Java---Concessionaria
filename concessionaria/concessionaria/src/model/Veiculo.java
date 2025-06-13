package model;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {
    private String marca;
    private String cor;
    private String modelo;
    private int ano;
    private float preco;

    public Veiculo(String marca, String modelo, int ano, String cor, float preco) {
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.modelo = modelo;
    }

    public abstract String exibirVeiculo();

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}
