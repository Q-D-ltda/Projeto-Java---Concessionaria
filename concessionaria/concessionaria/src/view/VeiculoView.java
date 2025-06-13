package view;

import java.util.List;
import java.util.Scanner;

import controller.VeiculoController;
import model.Carro;
import model.Moto;
import model.Veiculo;

public class VeiculoView implements IMenuView {
    private VeiculoController veiculoController;
    private Scanner scanner;

    public VeiculoView(VeiculoController veiculoController, Scanner scanner) {
        this.veiculoController = veiculoController;
        this.scanner = scanner;
    }

    public void menu() {
        int opcoes;
        do {
            System.out.println("\n--- Veículos ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Remover");
            System.out.println("4. Editar");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcoes = Integer.parseInt(scanner.nextLine());

            switch (opcoes) {
                case 1 -> listarVeiculos();
                case 2 -> adicionarVeiculo();
                case 3 -> removerVeiculo();
                case 4 -> editarVeiculo();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcoes != 0);
    }

    private void listarVeiculos() {
        List<Veiculo> veiculos;
        while (true) {
            System.out.println("\n--- Filtrar veículos ---");
            System.out.println("0 - Listar todos");
            System.out.println("1 - Filtrar por marca");
            System.out.println("2 - Filtrar por tipo (carro/moto)");
            System.out.println("3 - Filtrar por ano");
            System.out.println("4 - Filtrar por cor");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma opção: ");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "0" -> {
                    veiculos = veiculoController.listarVeiculos();
                }
                case "1" -> {
                    System.out.print("Digite a marca: ");
                    String marca = scanner.nextLine();
                    veiculos = veiculoController.listarVeiculos(marca);
                }
                case "2" -> {
                    System.out.print("Digite o tipo (carro/moto): ");
                    String tipo = scanner.nextLine().toLowerCase();
                    if (!tipo.equals("carro") && !tipo.equals("moto")) {
                        System.out.println("Tipo inválido. Use 'carro' ou 'moto'.");
                        continue;
                    }
                    veiculos = veiculoController.listarVeiculos(tipo, true);
                }
                case "3" -> {
                    System.out.print("Digite o ano: ");
                    try {
                        int ano = Integer.parseInt(scanner.nextLine());
                        veiculos = veiculoController.listarVeiculos(ano);
                    } catch (NumberFormatException e) {
                        System.out.println("Ano inválido. Digite um número.");
                        continue;
                    }
                }
                case "4" -> {
                    System.out.print("Digite a cor: ");
                    String cor = scanner.nextLine();
                    veiculos = veiculoController.listarVeiculosPorCor(cor);
                }
                case "5" -> {
                    return;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }
            }

            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo encontrado com esse filtro.");
            } else {
                int i = 0;
                for (Veiculo v : veiculos) {
                    System.out.println(i + " - " + v.exibirVeiculo());
                    i++;
                }
            }
            break;
        }
    }

    private void adicionarVeiculo() {
        int tipo;
        do {
            System.out.println("\nAdicionar veículo:");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            tipo = Integer.parseInt(scanner.nextLine());

            switch (tipo) {
                case 1 -> adicionarCarro();
                case 2 -> adicionarMoto();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (tipo != 0);
    }

    private void adicionarCarro() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Preço: ");
        float preco = Float.parseFloat(scanner.nextLine());
        System.out.print("Número de portas: ");
        int portas = Integer.parseInt(scanner.nextLine());

        veiculoController.adicionarVeiculo(new Carro(marca, modelo, ano, cor, preco, portas));
        System.out.println("Carro adicionado com sucesso.");
    }

    private void adicionarMoto() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Preço: ");
        float preco = Float.parseFloat(scanner.nextLine());
        System.out.print("Tipo (ex: esportiva, scooter): ");
        String tipo = scanner.nextLine();

        veiculoController.adicionarVeiculo(new Moto(marca, ano, cor, preco, tipo, modelo));
        System.out.println("Moto adicionada com sucesso.");
    }

    private void removerVeiculo() {
        List<Veiculo> lista = veiculoController.listarVeiculos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum veículo disponível para remover.");
            return;
        }

        System.out.println("\n--- Veículos Cadastrados ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i));
        }

        System.out.print("Índice do veículo para remover: ");
        int idx = Integer.parseInt(scanner.nextLine());

        if (idx >= 0 && idx < lista.size()) {
            veiculoController.removerVeiculo(lista.get(idx));
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void editarVeiculo() {
        List<Veiculo> veiculos = veiculoController.listarVeiculos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(i + " - " + veiculos.get(i).exibirVeiculo());
        }

        System.out.print("Digite o código (índice) do veículo para editar: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
            return;
        }

        if (codigo < 0 || codigo >= veiculos.size()) {
            System.out.println("Código inválido.");
            return;
        }

        Veiculo veiculoSelecionado = veiculos.get(codigo);

        System.out.print("Digite a nova marca (atual: " + veiculoSelecionado.getMarca() + "): ");
        String novaMarca = scanner.nextLine();
        if (!novaMarca.isBlank()) {
            veiculoSelecionado.setMarca(novaMarca);
        }

        System.out.print("Digite o novo modelo (atual: " + veiculoSelecionado.getModelo() + "): ");
        String novoModelo = scanner.nextLine();
        if (!novoModelo.isBlank()) {
            veiculoSelecionado.setModelo(novoModelo);
        }

        System.out.print("Digite o novo ano (atual: " + veiculoSelecionado.getAno() + "): ");
        String inputAno = scanner.nextLine();
        if (!inputAno.isBlank()) {
            try {
                int novoAno = Integer.parseInt(inputAno);
                veiculoSelecionado.setAno(novoAno);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido, mantendo o atual.");
            }
        }

        System.out.print("Digite a nova cor (atual: " + veiculoSelecionado.getCor() + "): ");
        String novaCor = scanner.nextLine();
        if (!novaCor.isBlank()) {
            veiculoSelecionado.setCor(novaCor);
        }

        System.out.print("Digite o novo preço (atual: R$ " + veiculoSelecionado.getPreco() + "): ");
        String inputPreco = scanner.nextLine();
        if (!inputPreco.isBlank()) {
            try {
                float novoPreco = Float.parseFloat(inputPreco);
                veiculoSelecionado.setPreco(novoPreco);
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido, mantendo o atual.");
            }
        }

        if (veiculoSelecionado instanceof Carro carro) {
            System.out.print("Digite o novo número de portas (atual: " + carro.getNumeroPortas() + "): ");
            String inputPortas = scanner.nextLine();
            if (!inputPortas.isBlank()) {
                try {
                    int portas = Integer.parseInt(inputPortas);
                    carro.setNumeroPortas(portas);
                } catch (NumberFormatException e) {
                    System.out.println("Número de portas inválido, mantendo o atual.");
                }
            }
        } else if (veiculoSelecionado instanceof Moto moto) {
            System.out.print("Digite o novo tipo (atual: " + moto.getTipo() + "): ");
            String novoTipo = scanner.nextLine();
            if (!novoTipo.isBlank()) {
                moto.setTipo(novoTipo);
            }
        }

        veiculoController.salvar();

        System.out.println("Veículo atualizado com sucesso!");
    }
}
