package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Venda implements Serializable{
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate data;
    private float valor;
    private boolean parcelada;
    private int parcelas;
    
    public Venda(Cliente cliente, Veiculo veiculo, LocalDate data, float valor, boolean parcelada, int parcelas) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.data = data;
        this.valor = valor;
        this.parcelada = parcelada;
        this.parcelas = parcelas;
    }
    
    public float calcularValorFinal(int parcelas) {
        if (parcelas <= 6) {
            return valor;
        } else {
            float taxa = 0.012f;
            float valorFinal = valor;
            for (int i = 0; i < parcelas; i++) {
                valorFinal *= (1 + taxa);
            }
            return valorFinal;
        }
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isParcelada() {
        return parcelada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        float valorFinal = parcelada ? calcularValorFinal(parcelas) : valor;

        return String.format("""
                Venda:
                  Cliente: %s
                  Veículo: %s
                  Data: %s
                  Valor Original: R$ %.2f%s
                """,
                cliente.getNome(),
                veiculo.exibirVeiculo(),
                data,
                valor,
                parcelada ? String.format("\n  Parcelado em %dx - Valor Final: R$ %.2f", parcelas, valorFinal) : "");
    }


}