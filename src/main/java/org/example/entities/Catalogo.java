package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "catalogo")
public abstract class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codIsbn;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private int dataPubblicazione;

    @Column(nullable = false)
    private int numeroPagine;

    @ManyToMany(mappedBy = "elementoPrestato")
    private List<Prestito> prestiti;


    public Catalogo() {
    }


    public Catalogo(String titolo, int dataPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public Long getCodIsbn() {
        return codIsbn;
    }

    public void setCodIsbn(Long codIsbn) {
        this.codIsbn = codIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(int dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "codIsbn=" + codIsbn +
                ", titolo='" + titolo + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
