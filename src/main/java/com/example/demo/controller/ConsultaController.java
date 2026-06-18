package com.example.demo.controller;

import com.example.demo.model.Consulta;
import com.example.demo.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/animal/{animalId}")
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta, @PathVariable Long animalId) {
        try {
            return ResponseEntity.ok(consultaService.incluir(consulta, animalId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Consulta> buscarTodas() {
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public List<Consulta> buscarPorAnimal(@PathVariable Long animalId) {
        return consultaService.listarPorAnimal(animalId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            return ResponseEntity.ok(consultaService.alterar(id, consulta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return consultaService.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}