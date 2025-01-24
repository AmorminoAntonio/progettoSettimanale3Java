package org.example.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToMany
    @JoinTable(
            name = "catalogo_prestiti",
            joinColumns = @JoinColumn(name = "prestito_id"),
            inverseJoinColumns = @JoinColumn(name = "catalogo_id")
    )
    private List<Catalogo> ElementoPrestato;

    private LocalDate DataInizioPrestito;
    private LocalDate DataRestituzionePrevista;
    private LocalDate DataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Utente utente, List<Catalogo> elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.utente = utente;
        this.ElementoPrestato = elementoPrestato;
        this.DataInizioPrestito = dataInizioPrestito;
        this.DataRestituzionePrevista = dataRestituzionePrevista;
        this.DataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Catalogo> getElementoPrestato() {
        return ElementoPrestato;
    }

    public void setElementoPrestato(List<Catalogo> elementoPrestato) {
        ElementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return DataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        DataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return DataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataInizioPrestito) {
        this.DataInizioPrestito = dataInizioPrestito;
        DataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return DataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        DataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", ElementoPrestato=" + ElementoPrestato +
                ", DataInizioPrestito=" + DataInizioPrestito +
                ", DataRestituzionePrevista=" + DataRestituzionePrevista +
                ", DataRestituzioneEffettiva=" + DataRestituzioneEffettiva +
                '}';
    }
}
