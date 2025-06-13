package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Moto;

public abstract class MotoDAO {
    private static final String CAMINHO = "src/dados/moto";

    public static void salvar(List<Moto> motos) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/motos.ser"))) {
            oos.writeObject(motos);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Moto> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/motos.ser");
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Moto>) ois.readObject();
        }
    }
}
