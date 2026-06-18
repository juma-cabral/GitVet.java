package construtores;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoTutores {
    private List<Tutor> tutores = new ArrayList<>();
    private int proximoId = 1;


    public Tutor incluirTutor(String nome, String telefone, String email, String cpf) {
        Tutor tutor = new Tutor(proximoId++, nome, telefone, email, cpf);
        tutores.add(tutor);
        System.out.println("Tutor cadastrado com sucesso! ID: " + tutor.getId());
        return tutor;
    }

 //LER
    public Tutor buscarPorId(int id) {
        return tutores.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Tutor> listarTodos() {
        return new ArrayList<>(tutores);
    }


    public boolean alterarTutor(int id, String novoNome, String novoTelefone,
                                String novoEmail, String novoCpf) {
        Tutor tutor = buscarPorId(id);
        if (tutor == null) {
            System.out.println("Tutor com ID " + id + "não encontrado.");
            return false;
        }
        if (novoNome != null && !novoNome.isBlank())           tutor.setNome(novoNome);
        if (novoTelefone != null && !novoTelefone.isBlank())   tutor.setTelefone(novoTelefone);
        if (novoEmail != null && !novoEmail.isBlank())         tutor.setEmail(novoEmail);
        if (novoCpf != null && !novoCpf.isBlank())             tutor.setCpf(novoCpf);
        System.out.println("Tutor ID " + id + "atualizado com sucesso!");
        return true;
    }


    public boolean excluirTutor(int id) {
        boolean removido = tutores.removeIf(t -> t.getId() == id);
        if (removido) {
            System.out.println("Tutor ID " + id + "removido com sucesso!");
        } else {
            System.out.println("Tutor com ID " + id + "não encontrado.");
        }
        return removido;
    }
}