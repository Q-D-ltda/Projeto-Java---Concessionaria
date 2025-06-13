package model;

public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String marca, String modelo, int ano, String cor, float preco, int numeroPortas) {
        super(marca, modelo, ano, cor, preco);
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    @Override
    public String exibirVeiculo() {
        return String.format("Carro:\n  Marca: %s\n  Modelo: %s\n  Ano: %d\n  Cor: %s\n  Portas: %d\n  Preço: R$ %.2f",
                getMarca(), getModelo(), getAno(), getCor(), getNumeroPortas(), getPreco());
    }

}
