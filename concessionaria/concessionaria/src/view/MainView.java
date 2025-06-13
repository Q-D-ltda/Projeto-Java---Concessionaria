package view;

import java.util.Scanner;

import controller.ClienteController;
import controller.VeiculoController;
import controller.VendaController;

public class MainView implements IMenuView {
    private Scanner scanner = new Scanner(System.in);
    private ClienteController clienteController = new ClienteController();
    private VeiculoController veiculoController = new VeiculoController();
    private VendaController vendaController = new VendaController();

    public void menu() {
        util.PreCarga.carregarDadosIniciais(clienteController, veiculoController, vendaController);
        int opcoes;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Clientes");
            System.out.println("2. Veículos");
            System.out.println("3. Vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcoes = Integer.parseInt(scanner.nextLine());

            switch (opcoes) {
                case 1 -> new ClienteView(clienteController, scanner).menu();
                case 2 -> new VeiculoView(veiculoController, scanner).menu();
                case 3 -> new VendaView(vendaController, clienteController, veiculoController, scanner).menu();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcoes != 0);
        scanner.close();
    }
}
