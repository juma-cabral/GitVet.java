package com.example.demo.controller;

import com.example.demo.model.Tutor;
import com.example.demo.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public Tutor criar(@RequestBody Tutor tutor) {
        return tutorService.incluir(tutor);
    }

    @GetMapping
    public List<Tutor> buscarTodos() {
        return tutorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> buscarPorId(@PathVariable Long id) {
        return tutorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutor> atualizar(@PathVariable Long id, @RequestBody Tutor tutor) {
        try {
            return ResponseEntity.ok(tutorService.alterar(id, tutor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return tutorService.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}