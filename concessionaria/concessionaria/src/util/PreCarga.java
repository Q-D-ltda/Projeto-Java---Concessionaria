package util;

import java.time.LocalDate;

import controller.ClienteController;
import controller.VeiculoController;
import controller.VendaController;
import model.Carro;
import model.Cliente;
import model.Moto;
import model.Venda;

public class PreCarga {

    public static void carregarDadosIniciais(
    ClienteController clienteController,
    VeiculoController veiculoController,
    VendaController vendaController
) {
    Cliente cliente1 = new Cliente("João Silva", "12345678900", "joao@email.com");
    Cliente cliente2 = new Cliente("Maria Souza", "98765432100", "maria@email.com");
    Cliente cliente3 = new Cliente("Carlos Lima", "45678912300", "carlos@email.com");
    Cliente cliente4 = new Cliente("Ana Paula", "32165498700", "ana@email.com");

    clienteController.adicionarCliente(cliente1);
    clienteController.adicionarCliente(cliente2);
    clienteController.adicionarCliente(cliente3);
    clienteController.adicionarCliente(cliente4);

    Carro carro1 = new Carro("Ford", "Fiesta", 2015, "Prata", 25000f, 4);
    Carro carro2 = new Carro("Chevrolet", "Onix", 2018, "Branco", 40000f, 4);
    Carro carro3 = new Carro("Volkswagen", "Gol", 2016, "Cinza", 30000f, 2);

    Moto moto1 = new Moto("Honda", 2020, "Vermelha", 15000f, "Esportiva", "CG 160");
    Moto moto2 = new Moto("Yamaha", 2021, "Preta", 18000f, "Scooter", "NMax");
    Moto moto3 = new Moto("Suzuki", 2019, "Azul", 17000f, "Custom", "Intruder");

    veiculoController.adicionarVeiculo(carro1);
    veiculoController.adicionarVeiculo(carro2);
    veiculoController.adicionarVeiculo(carro3);
    veiculoController.adicionarVeiculo(moto1);
    veiculoController.adicionarVeiculo(moto2);
    veiculoController.adicionarVeiculo(moto3);

    Carro vendido1 = new Carro("Renault", "Kwid", 2022, "Laranja", 35000f, 4);
    Moto vendido2 = new Moto("Dafra", 2018, "Branca", 12000f, "Street", "Apache");

    veiculoController.adicionarVeiculo(vendido1);
    veiculoController.adicionarVeiculo(vendido2);

    Venda venda1 = new Venda(cliente1, vendido1, LocalDate.parse("2025-06-11"), 35000f, true, 12); // parcelada
    Venda venda2 = new Venda(cliente2, vendido2, LocalDate.parse("2025-05-20"), 12000f, false, 1); // à vista

    Carro carroVendido3 = new Carro("Hyundai", "HB20", 2019, "Preto", 45000f, 4);
    veiculoController.adicionarVeiculo(carroVendido3);
    Venda venda3 = new Venda(cliente3, carroVendido3, LocalDate.parse("2025-04-10"), 45000f, true, 8);

    Moto motoVendida4 = new Moto("BMW", 2023, "Branca", 55000f, "Adventure", "GS 310");
    veiculoController.adicionarVeiculo(motoVendida4);
    Venda venda4 = new Venda(cliente4, motoVendida4, LocalDate.parse("2025-03-01"), 55000f, false, 1);

    vendaController.adicionarVenda(venda1, veiculoController);
    vendaController.adicionarVenda(venda2, veiculoController);
    vendaController.adicionarVenda(venda3, veiculoController);
    vendaController.adicionarVenda(venda4, veiculoController);
}

}
