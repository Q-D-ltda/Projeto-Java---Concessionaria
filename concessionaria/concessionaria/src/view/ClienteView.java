package view;

import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;

public class ClienteView implements IMenuView {
    private ClienteController clienteController;
    private Scanner scanner;

    public ClienteView(ClienteController clienteController, Scanner scanner) {
        this.clienteController = clienteController;
        this.scanner = scanner;
    }

    public void menu() {
        int opcoes;
        do {
            System.out.println("\n--- Clientes ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Remover");
            System.out.println("4. Atualizar");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcoes = Integer.parseInt(scanner.nextLine());

            switch (opcoes) {
                case 1 -> listarClientes();
                case 2 -> adicionarCliente();
                case 3 -> removerCliente();
                case 4 -> atualizarCliente();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcoes != 0);
    }

    private void listarClientes() {
        clienteController.listarTodos().forEach(System.out::println);
    }

    private void adicionarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        boolean adicionado = clienteController.adicionarCliente(new Cliente(nome, cpf, email));
        System.out.println(adicionado ? "Cliente adicionado." : "CPF já cadastrado.");
    }

    private void removerCliente() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        boolean removido = clienteController.removerCliente(cpf);
        System.out.println(removido ? "Removido com sucesso." : "Cliente não encontrado.");
    }

    private void atualizarCliente() {
        System.out.print("CPF do cliente a atualizar: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        boolean atualizado = clienteController.atualizarCliente(new Cliente(nome, cpf, email));
        System.out.println(atualizado ? "Atualizado com sucesso." : "Cliente não encontrado.");
    }
}
