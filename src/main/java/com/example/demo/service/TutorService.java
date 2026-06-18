package com.example.demo.service;

import com.example.demo.model.Tutor;
import com.example.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Tutor incluir(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Optional<Tutor> buscarPorId(Long id) {
        return tutorRepository.findById(id);
    }

    public Tutor alterar(Long id, Tutor novosDados) {
        return tutorRepository.findById(id).map(tutor -> {
            if (novosDados.getNome() != null && !novosDados.getNome().isBlank()) tutor.setNome(novosDados.getNome());
            if (novosDados.getTelefone() != null && !novosDados.getTelefone().isBlank()) tutor.setTelefone(novosDados.getTelefone());
            if (novosDados.getEmail() != null && !novosDados.getEmail().isBlank()) tutor.setEmail(novosDados.getEmail());
            if (novosDados.getCpf() != null && !novosDados.getCpf().isBlank()) tutor.setCpf(novosDados.getCpf());
            return tutorRepository.save(tutor);
        }).orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + id));
    }

    public boolean excluir(Long id) {
        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}