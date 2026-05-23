public class Contato {

    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {

        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Converte o objeto para formato de arquivo
    public String paraLinha(){
        return nome + ";" + telefone;
    }

    // Reconstrói o objeto usando a linha do arquivo
    public static Contato deLinha(String linha){
        String [] dados = linha.split(";");
        return new Contato ( dados[0],dados[1]);
    }

    @Override
    public String toString() {

        return "Nome: " + nome +
               " | Telefone: " + telefone;
    }
}