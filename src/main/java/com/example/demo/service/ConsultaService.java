package com.example.demo.service;

import com.example.demo.model.Animal;
import com.example.demo.model.Consulta;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public Consulta incluir(Consulta consulta, Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + animalId));
        consulta.setAnimal(animal);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> listarPorAnimal(Long animalId) {
        return consultaRepository.findByAnimalId(animalId);
    }

    public Consulta alterar(Long id, Consulta novosDados) {
        return consultaRepository.findById(id).map(consulta -> {
            if (novosDados.getData() != null && !novosDados.getData().isBlank()) consulta.setData(novosDados.getData());
            if (novosDados.getHorario() != null && !novosDados.getHorario().isBlank()) consulta.setHorario(novosDados.getHorario());
            if (novosDados.getVeterinario() != null && !novosDados.getVeterinario().isBlank()) consulta.setVeterinario(novosDados.getVeterinario());
            if (novosDados.getMotivo() != null && !novosDados.getMotivo().isBlank()) consulta.setMotivo(novosDados.getMotivo());
            if (novosDados.getObservacoes() != null) consulta.setObservacoes(novosDados.getObservacoes());
            return consultaRepository.save(consulta);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public boolean excluir(Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}