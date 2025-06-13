package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dal.ClienteDAO;
import model.Cliente;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        try {
            clientes = ClienteDAO.carregar();
        } catch (IOException | ClassNotFoundException e) {
            clientes = new ArrayList<>();
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (buscarPorCpf(cliente.getCpf()).isPresent()) {
            return false;
        }
        clientes.add(cliente);
        salvar();
        return true;
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean atualizarCliente(Cliente clienteAtualizado) {
        Optional<Cliente> clienteOpt = buscarPorCpf(clienteAtualizado.getCpf());
        if (clienteOpt.isEmpty()) {
            return false;
        }
        Cliente cliente = clienteOpt.get();
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        salvar();
        return true;
    }

    public boolean removerCliente(String cpf) {
        Optional<Cliente> clienteOpt = buscarPorCpf(cpf);
        if (clienteOpt.isEmpty()) {
            return false;
        }
        clientes.remove(clienteOpt.get());
        salvar();
        return true;
    }

    private void salvar() {
        try {
            ClienteDAO.salvar(clientes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
