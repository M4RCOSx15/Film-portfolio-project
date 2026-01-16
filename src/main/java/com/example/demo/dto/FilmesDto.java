package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.client.RestTemplate;

//Classe JSON
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmesDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title") // O TMDB manda "title"
    private String titulo;

    @JsonProperty("overview") // O TMDB manda "overview" (sinopse)
    private String revisao;
    @JsonProperty("gender")
    private String genero;
    @JsonProperty("release_date") // O TMDB manda "2014-11-05"
    private String dataLancamento;

    @JsonProperty("poster_path") // Caminho da imagem: "/xJHokMv...jpg"
    private String posterPath;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno(){
        if(dataLancamento != null && dataLancamento.length()>=4){ //VERIFICA SE RETORNOU UMA DATA
            return Integer.parseInt(dataLancamento.substring(0,4)); //"TESOURA" PARA CORTAR E DEIXAR O ANO
        }
        return 0;
    }

    public String getUrlImagemCompleta(){
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRevisao() {
        return revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
