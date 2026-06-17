package construtores;

import java.util.ArrayList;
import java.util.List;

public class GerenciamentoAnimais {
    private List<Animal> animais = new ArrayList<>();
    private int proximoId = 1;
    private GerenciamentoTutores gerenciamentoTutores;

    public GerenciamentoAnimais(GerenciamentoTutores gerenciamentoTutores) {
        this.gerenciamentoTutores = gerenciamentoTutores;
    }


    public Animal incluirAnimal(String nome, String especie, String raca,
                                int idade, int tutorId) {
        Tutor tutor = gerenciamentoTutores.buscarPorId(tutorId);
        if (tutor == null) {
            System.out.println("Tutor com ID " + tutorId + " não encontrado.");
            return null;
        }
        Animal animal = new Animal(proximoId++, nome, especie, raca, idade, tutorId);
        animais.add(animal);
        System.out.println("Animal cadastrado com sucesso! ID: " + animal.getId());
        return animal;
    }

    //LER
    public Animal buscarPorId(int id) {
        return animais.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais);
    }


    public boolean alterarAnimal(int id, String novoNome, String novaEspecie,
                                 String novaRaca, int novaIdade, int novoTutorId) {
        Animal animal = buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal com ID " + id + " não encontrado.");
            return false;
        }
        if (novoTutorId > 0) {
            Tutor tutor = gerenciamentoTutores.buscarPorId(novoTutorId);
            if (tutor == null) {
                System.out.println("Tutor com ID " + novoTutorId + " não encontrado.");
                return false;
            }
            animal.setTutorId(novoTutorId);
        }
        if (novoNome != null && !novoNome.isBlank())       animal.setNome(novoNome);
        if (novaEspecie != null && !novaEspecie.isBlank()) animal.setEspecie(novaEspecie);
        if (novaRaca != null && !novaRaca.isBlank())       animal.setRaca(novaRaca);
        if (novaIdade > 0)                                 animal.setIdade(novaIdade);
        System.out.println("Animal ID " + id + " atualizado com sucesso!");
        return true;
    }


    public boolean excluirAnimal(int id) {
        boolean removido = animais.removeIf(a -> a.getId() == id);
        if (removido) {
            System.out.println("Animal ID " + id + " removido com sucesso!");
        } else {
            System.out.println("Animal com ID " + id + " não encontrado.");
        }
        return removido;
    }

    // AUXILIAR
    public GerenciamentoTutores getGerenciamentoTutores() {
        return gerenciamentoTutores;
    }
}