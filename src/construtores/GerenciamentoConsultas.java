package  construtores;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoConsultas {
    private List<Consulta> consultas = new ArrayList<>();
    private int proximoId = 1;
    private GerenciamentoAnimais gerenciamentoAnimais;

    public GerenciamentoConsultas(GerenciamentoAnimais gerenciamentoAnimais) {
        this.gerenciamentoAnimais = gerenciamentoAnimais;
    }


    public Consulta incluirConsulta(int animalId, String data, String horario,
                                    String veterinario, String motivo, String observacoes) {
        Animal animal = gerenciamentoAnimais.buscarPorId(animalId);
        if (animal == null) {
            System.out.println("  ✗ Animal com ID " + animalId + " não encontrado.");
            return null;
        }
        Consulta consulta = new Consulta(proximoId++, animalId, data, horario,
                veterinario, motivo, observacoes);
        consultas.add(consulta);
        System.out.println("  ✓ Consulta cadastrada com sucesso! ID: " + consulta.getId());
        return consulta;
    }

    // LER
    public Consulta buscarPorId(int id) {
        return consultas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Consulta> listarTodas() {
        return new ArrayList<>(consultas);
    }

    public List<Consulta> listarPorAnimal(int animalId) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getAnimalId() == animalId) resultado.add(c);
        }
        return resultado;
    }

    // ALTERAR
    public boolean alterarConsulta(int id, String novaData, String novoHorario,
                                   String novoVeterinario, String novoMotivo,
                                   String novasObservacoes) {
        Consulta consulta = buscarPorId(id);
        if (consulta == null) {
            System.out.println("  ✗ Consulta com ID " + id + " não encontrada.");
            return false;
        }
        if (novaData != null && !novaData.isBlank())          consulta.setData(novaData);
        if (novoHorario != null && !novoHorario.isBlank())    consulta.setHorario(novoHorario);
        if (novoVeterinario != null && !novoVeterinario.isBlank()) consulta.setVeterinario(novoVeterinario);
        if (novoMotivo != null && !novoMotivo.isBlank())      consulta.setMotivo(novoMotivo);
        if (novasObservacoes != null)                         consulta.setObservacoes(novasObservacoes);
        System.out.println("  ✓ Consulta ID " + id + " atualizada com sucesso!");
        return true;
    }

    // EXCLUIR
    public boolean excluirConsulta(int id) {
        boolean removido = consultas.removeIf(c -> c.getId() == id);
        if (removido) {
            System.out.println("  ✓ Consulta ID " + id + " removida com sucesso!");
        } else {
            System.out.println("  ✗ Consulta com ID " + id + " não encontrada.");
        }
        return removido;
    }
}