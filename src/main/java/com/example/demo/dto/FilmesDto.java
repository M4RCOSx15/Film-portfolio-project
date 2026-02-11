package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.client.RestTemplate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmesDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title") 
    private String titulo;

    @JsonProperty("overview") 
    private String revisao;
    @JsonProperty("gender")
    private String genero;
    @JsonProperty("release_date")
    private String dataLancamento;

    @JsonProperty("poster_path") 
    private String posterPath;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno(){
        if(dataLancamento != null && dataLancamento.length()>=4){ 
            return Integer.parseInt(dataLancamento.substring(0,4)); 
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
