package construtores;

public class Tutor {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public Tutor(int id, String nome, String telefone, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    // Getters
    public int getId()          { return id; }
    public String getNome()     { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail()    { return email; }
    public String getCpf()      { return cpf; }

    // Setters
    public void setNome(String nome)         { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email)       { this.email = email; }
    public void setCpf(String cpf)           { this.cpf = cpf; }

    @Override
    public String toString() {
        return String.format(
                "╔══════════════════════════════╗\n" +
                        "  Tutor ID:  %d\n" +
                        "  Nome:      %s\n" +
                        "  Telefone:  %s\n" +
                        "  Email:     %s\n" +
                        "  CPF:       %s\n" +
                        "╚══════════════════════════════╝",
                id, nome, telefone, email, cpf
        );
    }
}