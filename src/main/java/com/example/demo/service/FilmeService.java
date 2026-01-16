package com.example.demo.service;
import com.example.demo.dto.FilmesDto;
import com.example.demo.model.Filme;
import com.example.demo.repository.FilmeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FilmeService {
    @Autowired
    private RestTemplate restTemplate;
    private final FilmeRepository filmeRepository;
    private final String API_KEY = "3a3dfb46e31b6a38db2f6cf667f3692f";
    private String nome;
    public Filme getById(Long id){
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    public Filme buscarESalvar(String nomeFilme) {
        // 1. Monta a URL
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + nomeFilme + "&language=pt-BR";

        // 2. Recebe a resposta bruta como um Map
        Map<String, Object> resposta = restTemplate.getForObject(url, Map.class);

        try {
            if (resposta != null && resposta.containsKey("results")) {
                List<Map<String, Object>> resultados = (List<Map<String, Object>>) resposta.get("results");

                if (!resultados.isEmpty()) {

                    Map<String, Object> dadosDofilme = resultados.get(0);


                    ObjectMapper mapper = new ObjectMapper();
                    FilmesDto filmesDto = mapper.convertValue(dadosDofilme, FilmesDto.class);


                    Filme filme = converterParaFilme(filmesDto);
                    return filmeRepository.save(filme);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar filme: " + e.getMessage());
        }
        return null;
    }
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
    public Filme converterParaFilme(FilmesDto filmesDto){
        Filme filme = new Filme();
        filme.setNome(filmesDto.getTitulo());
        filme.setAno(filmesDto.getAno());
        filme.setDescricao(filmesDto.getRevisao());
        filme.setUrl_imagem(filmesDto.getUrlImagemCompleta());
      return filme;
    }
}
