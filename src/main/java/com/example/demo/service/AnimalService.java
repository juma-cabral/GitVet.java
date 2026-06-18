package com.example.demo.service;

import com.example.demo.model.Animal;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public Animal incluir(Animal animal, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com ID: " + tutorId));
        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Optional<Animal> buscarPorId(Long id) {
        return animalRepository.findById(id);
    }

    public Animal alterar(Long id, Animal novosDados, Long novoTutorId) {
        return animalRepository.findById(id).map(animal -> {
            if (novoTutorId != null) {
                Tutor novoTutor = tutorRepository.findById(novoTutorId)
                        .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
                animal.setTutor(novoTutor);
            }
            if (novosDados.getNome() != null && !novosDados.getNome().isBlank()) animal.setNome(novosDados.getNome());
            if (novosDados.getEspecie() != null && !novosDados.getEspecie().isBlank()) animal.setEspecie(novosDados.getEspecie());
            if (novosDados.getRaca() != null && !novosDados.getRaca().isBlank()) animal.setRaca(novosDados.getRaca());
            if (novosDados.getIdade() > 0) animal.setIdade(novosDados.getIdade());
            return animalRepository.save(animal);
        }).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    public boolean excluir(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}