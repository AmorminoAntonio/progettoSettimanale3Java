package org.example.entities;


import org.example.enumerated.TipoPeriodicità;

import javax.persistence.*;


@Entity

public class Rivista extends Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codIsbn;


    @Enumerated(EnumType.STRING)
    private TipoPeriodicità periodicità;


    public Rivista() {
    }

    public Rivista(String titolo, int dataPubblicazione, int numeroPagine, TipoPeriodicità periodicità) {
        super(titolo, dataPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    @Override
    public Long getCodIsbn() {
        return codIsbn;
    }

    @Override
    public void setCodIsbn(Long codIsbn) {
        this.codIsbn = codIsbn;
    }

    public TipoPeriodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(TipoPeriodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicità=" + periodicità +
                '}';
    }
}
