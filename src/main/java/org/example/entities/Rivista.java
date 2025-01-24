package org.example.entities;


import org.example.enumerated.TipoPeriodicità;

import javax.persistence.*;



@Entity
@Table(name = "riviste")

public class Rivista extends Catalogo {


    @Enumerated(EnumType.STRING)
    private TipoPeriodicità periodicità;


    public Rivista() {
    }

    public Rivista(String titolo, int dataPubblicazione, int numeroPagine, TipoPeriodicità periodicità) {
        super(titolo, dataPubblicazione, numeroPagine);
        this.periodicità = periodicità;
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
