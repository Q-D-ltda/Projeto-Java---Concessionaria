package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dal.CarroDAO;
import dal.MotoDAO;
import model.Veiculo;
import model.Carro;
import model.Moto;

public class VeiculoController {
    private List<Veiculo> veiculos;

    public VeiculoController() {
        veiculos = new ArrayList<>();
        carregar();
    }

    private void carregar() {
        try {
            veiculos.addAll(CarroDAO.carregar());
            veiculos.addAll(MotoDAO.carregar());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar veículos: " + e.getMessage());
        }
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvar();
    }

    public List<Veiculo> listarVeiculos() {
    return veiculos;
}

public List<Veiculo> listarVeiculos(String marca) {
    return veiculos.stream()
                   .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                   .toList();
}

public List<Veiculo> listarVeiculos(String tipo, boolean isTipo) {
    if (!isTipo) return new ArrayList<>();
    return veiculos.stream()
                   .filter(v -> {
                       if (tipo.equalsIgnoreCase("carro")) return v instanceof Carro;
                       if (tipo.equalsIgnoreCase("moto")) return v instanceof Moto;
                       return false;
                   })
                   .toList();
}

public List<Veiculo> listarVeiculos(int ano) {
    return veiculos.stream()
                   .filter(v -> v.getAno() == ano)
                   .toList();
}

public List<Veiculo> listarVeiculosPorCor(String cor) {
    return veiculos.stream()
                   .filter(v -> v.getCor().equalsIgnoreCase(cor))
                   .toList();
}
    
    public boolean existeVeiculo(Veiculo veiculo) {
        return veiculos.contains(veiculo);
    }

    public List<Veiculo> listarPorMarca(String marca) {
        return veiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
        salvar();
    }

    public void salvar() {
        List<Carro> carros = new ArrayList<>();
        List<Moto> motos = new ArrayList<>();

        for (Veiculo v : veiculos) {
            if (v instanceof Carro) {
                carros.add((Carro) v);
            } else if (v instanceof Moto) {
                motos.add((Moto) v);
            }
        }

        try {
            CarroDAO.salvar(carros);
            MotoDAO.salvar(motos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }
}
