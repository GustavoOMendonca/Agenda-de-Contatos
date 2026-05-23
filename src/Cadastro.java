import java.util.List;

public class Cadastro {

    private List<Contato> contatos;

    public Cadastro() {

        contatos = Persistencia.carregar();
    }

    public void adicionarContato() {

        String nome =
                utils.Entrada.lerTexto(
                        "Digite o nome do contato: "
                );

        String telefone =
                utils.Entrada.lerTexto(
                        "Digite o telefone: "
                );

        Contato contato =
                new Contato(nome, telefone);

        contatos.add(contato);

        Persistencia.salvar(contatos);

        System.out.println(
                "Contato adicionado com sucesso."
        );
    }

    public void listarContatos() {

        if (contatos.isEmpty()) {

            System.out.println(
                    "Nenhum contato cadastrado."
            );

            return;
        }

        System.out.println("\n===== CONTATOS =====");

        for (int i = 0; i < contatos.size(); i++) {

            System.out.println(
                    (i + 1) + " - " + contatos.get(i)
            );
        }
    }

    public void buscarContato() {

        String busca =
                utils.Entrada.lerTexto(
                        "Digite o nome do contato: "
                );

        boolean encontrado = false;

        for (Contato contato : contatos) {

            if (
                contato.getNome()
                        .equalsIgnoreCase(busca)
            ) {

                System.out.println(contato);

                encontrado = true;
            }
        }

        if (!encontrado) {

            System.out.println(
                    "Contato não encontrado."
            );
        }
    }

    public void atualizarContato() {

        listarContatos();

        if (contatos.isEmpty()) {
            return;
        }

        int indice =
                utils.Entrada.lerInt(
                        "Digite o número do contato: "
                );

        if (
            indice <= 0 ||
            indice > contatos.size()
        ) {

            System.out.println("Contato inválido.");

            return;
        }

        String novoNome =
                utils.Entrada.lerTexto(
                        "Novo nome: "
                );

        String novoTelefone =
                utils.Entrada.lerTexto(
                        "Novo telefone: "
                );

        Contato contato =
                contatos.get(indice - 1);

        contato.setNome(novoNome);

        contato.setTelefone(novoTelefone);

        Persistencia.salvar(contatos);

        System.out.println(
                "Contato atualizado."
        );
    }

    public void removerContato() {

        listarContatos();

        if (contatos.isEmpty()) {
            return;
        }

        int indice =
                utils.Entrada.lerInt(
                        "Digite o número do contato: "
                );

        if (
            indice <= 0 ||
            indice > contatos.size()
        ) {

            System.out.println("Contato inválido.");

            return;
        }

        contatos.remove(indice - 1);

        Persistencia.salvar(contatos);

        System.out.println(
                "Contato removido."
        );
    }
}
