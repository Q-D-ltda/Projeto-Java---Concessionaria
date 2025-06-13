package model;

public class Moto extends Veiculo {
    private String tipo;

    public Moto(String marca, int ano, String cor, float preco, String tipo, String modelo) {
        super(marca, modelo, ano, cor, preco);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String exibirVeiculo() {
        return String.format("Moto:\n  Marca: %s\n  Modelo: %s\n  Ano: %d\n  Cor: %s\n  Tipo: %s\n  Preço: R$ %.2f",
                getMarca(), getModelo(), getAno(), getCor(), getTipo(), getPreco());
    }

    
}
