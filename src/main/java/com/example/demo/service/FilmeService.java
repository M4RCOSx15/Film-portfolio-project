package com.example.demo.service;
import com.example.demo.model.Filme;
import com.example.demo.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> getAll(){
        return filmeRepository.findAll();
    }

    public Filme save(Filme filme){
        return filmeRepository.save(filme);
    }
    public void delete(Long id){
        filmeRepository.deleteById(id);
    }

}
