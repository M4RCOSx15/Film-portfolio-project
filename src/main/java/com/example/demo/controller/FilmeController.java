package com.example.demo.controller;

import com.example.demo.model.Filme;
import com.example.demo.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("FilmesPortifolio")
@CrossOrigin(origins = "*")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }
    @GetMapping("/{id}")
    public Filme getById(@PathVariable Long id) {
        return filmeService.getById(id);
    }

    @GetMapping
    public List<Filme> getAll(){
        return filmeService.getAll();
    }
    @PostMapping
    public Filme create(@RequestBody Filme filme){
        return filmeService.save(filme);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        filmeService.delete(id);
    }
    @PostMapping("/buscar")
    public Filme buscarNovonoTMDB(@RequestParam String nome){
        return filmeService.buscarESalvar(nome);
    }
}
