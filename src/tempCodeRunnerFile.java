public class Main {

    public static void main(String[] args) {

        Cadastro cadastro = new Cadastro();

        int opcao;

        do {

            System.out.println("\n===== AGENDA DE CONTATOS =====");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Atualizar contato");
            System.out.println("5 - Remover contato");
            System.out.println("0 - Sair");

            opcao = utils.Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    cadastro.adicionarContato();
                    break;

                case 2:
                    cadastro.listarContatos();
                    break;

                case 3:
                    cadastro.buscarContato();
                    break;

                case 4:
                    cadastro.atualizarContato();
                    break;

                case 5:
                    cadastro.removerContato();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}