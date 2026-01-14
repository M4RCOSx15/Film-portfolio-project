package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Table(name="filmes_web")
public class Filme {
    private String nome;
    private int ano;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String genero;
    protected Filme() {
    }
    public Filme(String nome, int ano, long id, String genero) {
        this.nome = nome;
        this.ano = ano;
        this.id = id;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
