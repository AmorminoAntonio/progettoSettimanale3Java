package org.example.dao;

import org.example.entities.Catalogo;
import org.example.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

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


    public void rimuoviElementoPerISBN(Long isbn) {
        try {
            Catalogo oggettoDaRimuovere = em.createQuery("SELECT c FROM Catalogo c WHERE c.codIsbn = :isbn", Catalogo.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
            if (oggettoDaRimuovere != null) {
                em.getTransaction().begin();
                em.remove(oggettoDaRimuovere);
                em.getTransaction().commit();
                System.out.println("Elemento rimosso con ISBN: " + oggettoDaRimuovere.getCodIsbn());
            }

        } catch (NoResultException e) {
            System.out.println("Elemento non trovato con ISBN: " + isbn);
        }
    }


    public void ricercaPerISBN(Long isbn) {
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


    public void ricercaPerTitolo(String titolo) {
        try {
            List<Catalogo> elementoConTitolo = em.createQuery("SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo", Catalogo.class)
                    .setParameter("titolo", "%" + titolo + "%")
                    .getResultList();
            if (elementoConTitolo.isEmpty()) {
                System.out.println("Nessun libro trovato per l'autore: " + titolo);
            } else {
                System.out.println("Libri trovati per l'autore: " + titolo);
                elementoConTitolo.forEach(System.out::println);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void ricercaElementiInPrestitoPerTesseraUtente(String numeroTessera) {

    }


    public void ricercaPrestitiScadutiNonRestituiti() {

    }
}
