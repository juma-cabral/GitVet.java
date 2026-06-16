package construtores;

public class Consulta {
    private int id;
    private int animalId;
    private String data;
    private String horario;
    private String veterinario;
    private String motivo;
    private String observacoes;

    public Consulta(int id, int animalId, String data, String horario,
                    String veterinario, String motivo, String observacoes) {
        this.id = id;
        this.animalId = animalId;
        this.data = data;
        this.horario = horario;
        this.veterinario = veterinario;
        this.motivo = motivo;
        this.observacoes = observacoes;
    }


    public int getId() { return id; }
    public int getAnimalId() { return animalId; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getVeterinario() { return veterinario; }
    public String getMotivo() { return motivo; }
    public String getObservacoes() { return observacoes; }


    public void setAnimalId(int animalId) { this.animalId = animalId; }
    public void setData(String data) { this.data = data; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return String.format(
                "╔══════════════════════════════╗\n" +
                        "  Consulta ID: %d\n" +
                        "  Animal ID:   %d\n" +
                        "  Data:        %s às %s\n" +
                        "  Veterinário: %s\n" +
                        "  Motivo:      %s\n" +
                        "  Observações: %s\n" +
                        "╚══════════════════════════════╝",
                id, animalId, data, horario, veterinario, motivo,
                (observacoes == null || observacoes.isEmpty() ? "—" : observacoes)
        );
    }
}