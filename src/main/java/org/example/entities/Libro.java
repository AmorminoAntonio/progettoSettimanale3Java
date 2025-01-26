package org.example.entities;


import org.example.enumerated.TipoGenere;

import javax.persistence.*;


@Entity
public class Libro extends Catalogo {


    @Column(name = "autore", nullable = false)
    private String autore;

    @Enumerated(EnumType.STRING)
    private TipoGenere genere;


    public Libro() {
    }

    public Libro(String titolo, int dataPubblicazione, int numeroPagine, String autore, TipoGenere genere) {
        super(titolo, dataPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public TipoGenere getGenere() {
        return genere;
    }

    public void setGenere(TipoGenere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", autore='" + autore + '\'' +
                ", genere=" + genere +
                '}';
    }
}
