package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dal.VendaDAO;
import model.Venda;

public class VendaController {
    private List<Venda> vendas;

    public VendaController() {
        try {
            vendas = VendaDAO.carregar();
        } catch (IOException | ClassNotFoundException e) {
            vendas = new ArrayList<>();
            System.err.println("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    public List<Venda> listarTodos() {
        return vendas;
    }

    public void adicionarVenda(Venda venda, VeiculoController veiculoController) {
        if (!veiculoController.existeVeiculo(venda.getVeiculo())) {
            System.err.println("Veículo não disponível para venda.");
            return;
        }

        vendas.add(venda);
        veiculoController.removerVeiculo(venda.getVeiculo());
        salvar();
    }

    public void adicionarVendaSemValidacao(Venda venda) {
        vendas.add(venda);
    }

    public boolean removerVenda(Venda venda) {
        boolean deletar = vendas.remove(venda);
        if (deletar) {
            salvar();
        }
        return deletar;
    }

    private void salvar() {
        try {
            VendaDAO.salvar(vendas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }
}
