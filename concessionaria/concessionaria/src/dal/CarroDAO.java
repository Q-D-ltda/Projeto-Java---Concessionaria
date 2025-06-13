package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public abstract class CarroDAO {
    private static final String CAMINHO = "src/dados/carro";

    public static void salvar(List<Carro> carros) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/carros.ser"))) {
            oos.writeObject(carros);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Carro> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/carros.ser");
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Carro>) ois.readObject();
        }
    }
}
