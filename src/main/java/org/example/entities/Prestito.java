package org.example.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "prestito_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToMany
    @JoinTable(
            name = "elemento_del_catalogo_prestiti",
            joinColumns = @JoinColumn(name = "prestito_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_catalogo_id")
    )
    private Set<Catalogo> elementoPrestato;

    private LocalDate DataInizioPrestito;
    private LocalDate DataRestituzionePrevista;
    @Column(nullable = true)
    private LocalDate DataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Utente utente, Set<Catalogo> elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
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

    public Set<Catalogo> getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Set<Catalogo> elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
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
                ", ElementoPrestato=" + elementoPrestato +
                ", DataInizioPrestito=" + DataInizioPrestito +
                ", DataRestituzionePrevista=" + DataRestituzionePrevista +
                ", DataRestituzioneEffettiva=" + DataRestituzioneEffettiva +
                '}';
    }
}
