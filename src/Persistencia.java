import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO = "contatos.txt";

    public static void salvar(List<Contato> contatos) {

        try (
            BufferedWriter writer =
                new BufferedWriter(
                    new FileWriter(ARQUIVO)
                )
        ) {

            for (Contato contato : contatos) {

                writer.write(contato.paraLinha());

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Erro ao salvar.");
        }
    }

    public static List<Contato> carregar() {

        List<Contato> contatos = new ArrayList<>();

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return contatos;
        }

        try (
            BufferedReader reader =
                new BufferedReader(
                    new FileReader(ARQUIVO)
                )
        ) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                contatos.add(
                    Contato.deLinha(linha)
                );
            }

        } catch (IOException e) {

            System.out.println("Erro ao carregar.");
        }

        return contatos;
    }
}