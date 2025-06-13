package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import controller.VeiculoController;
import controller.VendaController;
import model.Cliente;
import model.Veiculo;
import model.Venda;

public class VendaView implements IMenuView {
    private VendaController vendaController;
    private ClienteController clienteController;
    private VeiculoController veiculoController;
    private Scanner scanner;

    public VendaView(VendaController vendaController, ClienteController clienteController,
            VeiculoController veiculoController, Scanner scanner) {
        this.vendaController = vendaController;
        this.clienteController = clienteController;
        this.veiculoController = veiculoController;
        this.scanner = scanner;
    }

    public void menu() {
        int opcoes;
        do {
            System.out.println("\n--- Vendas ---");
            System.out.println("1. Listar Vendas");
            System.out.println("2. Realizar Venda");
            System.out.println("3. Remover do Sistema");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcoes = Integer.parseInt(scanner.nextLine());

            switch (opcoes) {
                case 1 -> listarVendas();
                case 2 -> realizarVenda();
                case 3 -> removerVenda();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcoes != 0);
    }

    private void listarVendas() {
        vendaController.listarTodos().forEach(System.out::println);
    }

    private void realizarVenda() {
        try {
            System.out.print("CPF do cliente: ");
            String cpf = scanner.nextLine();
            var optionalCliente = clienteController.buscarPorCpf(cpf);
            if (optionalCliente.isEmpty()) {
                System.out.println("Cliente não encontrado.");
                return;
            }
            Cliente cliente = optionalCliente.get();

            List<Veiculo> veiculos = veiculoController.listarVeiculos();
            for (int i = 0; i < veiculos.size(); i++) {
                System.out.println(i + " - " + veiculos.get(i).exibirVeiculo());
            }
            System.out.print("Índice do veículo: ");
            int idx = Integer.parseInt(scanner.nextLine());
            Veiculo veiculo = veiculos.get(idx);

            boolean parcelada = false;
            int parcelas = 1;

            while (true) {
                System.out.print("Parcelada? Juros de 1,2% ao mês a partir de 7x (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();

                if (resposta.equals("s")) {
                    parcelada = true;

                    while (true) {
                        System.out.print("Número de parcelas: ");
                        try {
                            parcelas = Integer.parseInt(scanner.nextLine());
                            if (parcelas < 1) {
                                System.out.println("Número de parcelas deve ser no mínimo 1.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Digite um número válido.");
                        }
                    }
                    break;

                } else if (resposta.equals("n")) {
                    parcelada = false;
                    parcelas = 1;
                    break;

                } else {
                    System.out.println("Resposta inválida. Digite apenas 's' ou 'n'.");
                }
            }

            Venda venda = new Venda(cliente, veiculo, LocalDate.now(), veiculo.getPreco(), parcelada, parcelas);

            vendaController.adicionarVenda(venda, veiculoController);
            veiculoController.removerVeiculo(veiculo);
            System.out.println("Venda realizada.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerVenda() {
    List<Venda> vendas = vendaController.listarTodos();
    if (vendas.isEmpty()) {
        System.out.println("Nenhuma venda cadastrada.");
        return;
    }

    System.out.println("\n--- Vendas Cadastradas ---");
    for (int i = 0; i < vendas.size(); i++) {
        System.out.println(i + " - " + vendas.get(i));
    }

    System.out.print("Índice da venda a remover: ");
    int idx = Integer.parseInt(scanner.nextLine());

    if (idx >= 0 && idx < vendas.size()) {
        vendaController.removerVenda(vendas.get(idx));
        System.out.println("Venda removida com sucesso.");
    } else {
        System.out.println("Índice inválido.");
    }
}


}
