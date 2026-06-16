package construtores;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private int tutorId;

    public Animal(int id, String nome, String especie, String raca, int idade, int tutorId) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.tutorId = tutorId;
    }

    // Getters
    public int getId()       { return id; }
    public String getNome()  { return nome; }
    public String getEspecie() { return especie; }
    public String getRaca()  { return raca; }
    public int getIdade()    { return idade; }
    public int getTutorId()  { return tutorId; }

    // Setters
    public void setNome(String nome)       { this.nome = nome; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setRaca(String raca)       { this.raca = raca; }
    public void setIdade(int idade)        { this.idade = idade; }
    public void setTutorId(int tutorId)    { this.tutorId = tutorId; }

    @Override
    public String toString() {
        return String.format(
                "╔══════════════════════════════╗\n" +
                        "  Animal ID: %d\n" +
                        "  Nome:      %s\n" +
                        "  Espécie:   %s\n" +
                        "  Raça:      %s\n" +
                        "  Idade:     %d ano(s)\n" +
                        "  Tutor ID:  %d\n" +
                        "╚══════════════════════════════╝",
                id, nome, especie, raca, idade, tutorId
        );
    }
}