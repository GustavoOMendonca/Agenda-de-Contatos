import java.util.ArrayList;

public class Cadastro {

    private ArrayList<String> contatos = new ArrayList<>();

    public void adicionarContato() {

        String nome = utils.Entrada.lerTexto("Digite o nome do contato: ");

        contatos.add(nome);

        System.out.println("Contato adicionado com sucesso.");
    }

    public void listarContatos() {

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }

        System.out.println("\n===== CONTATOS =====");

        for (int i = 0; i < contatos.size(); i++) {
            System.out.println((i + 1) + " - " + contatos.get(i));
        }
    }

    public void buscarContato() {

        String busca = utils.Entrada.lerTexto("Digite o nome do contato: ");

        boolean encontrado = false;

        for (String contato : contatos) {

            if (contato.equalsIgnoreCase(busca)) {

                System.out.println("Contato encontrado: " + contato);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Contato não encontrado.");
        }
    }

    public void atualizarContato() {

        listarContatos();

        if (contatos.isEmpty()) {
            return;
        }

        int indice = utils.Entrada.lerInt("Digite o número do contato: ");

        if (indice <= 0 || indice > contatos.size()) {

            System.out.println("Contato inválido.");
            return;
        }

        String novoNome = utils.Entrada.lerTexto("Digite o novo nome: ");

        contatos.set(indice - 1, novoNome);

        System.out.println("Contato atualizado.");
    }

    public void removerContato() {

        listarContatos();

        if (contatos.isEmpty()) {
            return;
        }

        int indice = utils.Entrada.lerInt("Digite o número do contato: ");

        if (indice <= 0 || indice > contatos.size()) {

            System.out.println("Contato inválido.");
            return;
        }

        contatos.remove(indice - 1);

        System.out.println("Contato removido.");
    }
}
