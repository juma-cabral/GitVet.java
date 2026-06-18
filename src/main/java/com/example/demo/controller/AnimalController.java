package com.example.demo.controller;

import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/tutor/{tutorId}")
    public ResponseEntity<Animal> criar(@RequestBody Animal animal, @PathVariable Long tutorId) {
        try {
            return ResponseEntity.ok(animalService.incluir(animal, tutorId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Animal> buscarTodos() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody Animal animal, @RequestParam(required = false) Long novoTutorId) {
        try {
            return ResponseEntity.ok(animalService.alterar(id, animal, novoTutorId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return animalService.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}