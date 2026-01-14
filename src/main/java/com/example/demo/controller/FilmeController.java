package com.example.demo.controller;

import com.example.demo.model.Filme;
import com.example.demo.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("FilmesPortifolio")
public class FilmeController {
    //injeção de dependecia
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<Filme> getAll(){
        return filmeService.getAll();
    }
    @PostMapping
    public Filme create(@RequestBody Filme filme){
        return filmeService.save(filme);
    }
    @DeleteMapping
    public void delete(Long id){
        filmeService.delete(id);
    }

}
