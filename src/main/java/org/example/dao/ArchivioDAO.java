package org.example.dao;

import org.example.entities.Catalogo;
import org.example.entities.Libro;
import org.example.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ArchivioDAO {

    private EntityManager em;

    public ArchivioDAO(EntityManager em) {
        this.em = em;
    }


    public void aggiungiElementoCatalogo(Catalogo c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        System.out.println(c.toString() + " <----Elemento aggiunto");
    }


    public void aggiornaElementoCatalogo(Catalogo c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        System.out.println(c.toString() + " <----Elemento aggiornato");
    }


    public void rimuoviElementoPerISBN(Long isbn) {
        try {
            Catalogo elementoDaRimuovere = em.createQuery("SELECT c FROM Catalogo c WHERE c.codIsbn = :isbn", Catalogo.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
            em.getTransaction().begin();
            em.remove(elementoDaRimuovere);
            em.getTransaction().commit();
            System.out.println("Elemento rimosso con ISBN: " + isbn);
        } catch (NoResultException e) {
            System.out.println("Elemento non trovato con ISBN: " + isbn);
        }
    }


    public Catalogo ricercaPerISBN(Long isbn) {
        try {
            Catalogo oggettoRicercato = em.createQuery("SELECT c FROM Catalogo c WHERE c.codIsbn = :isbn", Catalogo.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
            if (oggettoRicercato != null) {
                System.out.println("elemento trovato con cod.ISBN: " + oggettoRicercato.getCodIsbn() + " -----> " + oggettoRicercato.getTitolo());
            }
        } catch (NoResultException e) {
            System.out.println("Elemento non trovato con ISBN: " + isbn);
        }
        return null;
    }


    public void ricercaPerAnnoPubblicazione(int anno) {
        try {
            List<Catalogo> risultati = em.createQuery("SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :anno", Catalogo.class)
                    .setParameter("anno", anno)
                    .getResultList();
            if (risultati.isEmpty()) {
                System.out.println("Nessun elemento trovato per l'anno di pubblicazione: " + anno);
            } else {
                risultati.forEach(elemento -> System.out.println("Elemento trovato: " + elemento));
            }
        } catch (Exception e) {
            System.out.println("Errore durante la ricerca: " + e.getMessage());
        }

    }


    public void ricercaPerAutore(String autore) {
        try {
            List<Catalogo> autoreCatalogo = em.createQuery("SELECT c FROM Catalogo c WHERE LOWER(c.autore) LIKE :autore", Catalogo.class)
                    .setParameter("autore", "%" + autore.toLowerCase().trim() + "%")
                    .getResultList();
            if (autoreCatalogo.isEmpty()) {
                System.out.println("Nessun libro trovato per l'autore: " + autore);
            } else {
                System.out.println("Libri trovati per l'autore: " + autore);
                autoreCatalogo.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " <----Errore durante la ricerca");
        }
    }


    public List<Catalogo> ricercaPerTitoloOparteDiEsso(String titolo) {
        return em.createQuery("SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo", Catalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }


    public List<Catalogo> ricercaElementiInPrestitoPerTesseraUtente(String numeroTessera) {
        return em.createQuery(
                        "SELECT c FROM Catalogo c " +
                                "JOIN c.prestiti p " +
                                "JOIN p.utente u " +
                                "WHERE u.numeroTessera = :numeroTessera AND p.DataRestituzioneEffettiva IS NULL",
                        Catalogo.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }


    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        LocalDate oggi = LocalDate.now();
        return em.createQuery(
                        "SELECT p FROM Prestito p " +
                                "WHERE p.DataRestituzionePrevista < :oggi AND p.DataRestituzioneEffettiva IS NULL",
                        Prestito.class)
                .setParameter("oggi", oggi)
                .getResultList();
    }
}
