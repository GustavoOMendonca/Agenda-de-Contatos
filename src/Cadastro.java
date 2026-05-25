import java.util.List;

public class Cadastro {

    private List<Contato> contatos;

    public Cadastro() {

        contatos = Persistencia.carregar();
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void adicionar(String nome, String telefone) throws Exception {
        validarDuplicado(nome, telefone, -1);
        contatos.add(new Contato(nome, telefone));
        Persistencia.salvar(contatos);
    }

    public void atualizar(int indice, String nome, String telefone) throws Exception {
        if (indice < 0 || indice >= contatos.size()) throw new Exception("Contato inválido.");
        
        validarDuplicado(nome, telefone, indice);
        
        Contato contato = contatos.get(indice);
        contato.setNome(nome);
        contato.setTelefone(telefone);
        Persistencia.salvar(contatos);
    }

    public void remover(int indice) {
        if (indice >= 0 && indice < contatos.size()) {
            contatos.remove(indice);
            Persistencia.salvar(contatos);
        }
    }

    private void validarDuplicado(String nome, String telefone, int indiceIgnorado) throws Exception {
        for (int i = 0; i < contatos.size(); i++) {
            if (i == indiceIgnorado) continue;
            Contato c = contatos.get(i);
            if (c.getNome().equalsIgnoreCase(nome)) {
                throw new Exception("Este nome já está cadastrado.");
            }
            if (c.getTelefone().equals(telefone)) {
                throw new Exception("Este telefone já está cadastrado.");
            }
        }
    }
}
