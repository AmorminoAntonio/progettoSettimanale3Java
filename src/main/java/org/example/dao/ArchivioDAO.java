package org.example.dao;

import org.example.entities.Catalogo;
import org.example.entities.Prestito;
import org.example.entities.Utente;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
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
            System.out.println("Elemento non trovato con ISBN: " + isbn + " <----Errore durante la rimozione");
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
            System.out.println("Elemento non trovato con ISBN: " + isbn + " <----Errore durante la ricerca");
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
            System.out.println(e.getMessage() + " <----Errore durante la ricerca");
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


    public void ricercaPrestitiTramiteNumeroTessera(String numeroTessera) {
        try {
            Utente utente = em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera", Utente.class)
                    .setParameter("numeroTessera", numeroTessera)
                    .getSingleResult();

            System.out.println("Utente trovato: " + utente.getNome() + ", ID: " + utente.getId() + ", Numero Tessera: " + utente.getNumeroTessera());

            List<Prestito> prestiti = utente.getElementiPresiInPrestito();

            if (prestiti.isEmpty()) {
                System.out.println("L'utente non ha alcun prestito.");
            } else {
                System.out.println("Prodotti presi in prestito:");

                for (Prestito prestito : prestiti) {
                    System.out.println("ID prestito: " + prestito.getId() +
                            " - Prestiti: " + prestito.getElementoPrestato() +
                            " - Data Prestito: " + prestito.getDataInizioPrestito());
                }
            }
        } catch (NoResultException e) {
            System.out.println("Utente con numero di tessera " + numeroTessera + " non trovato.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " <----Errore durante la ricerca");
        }
    }


    public void ricercaPrestitiScadutiNonRestituiti() {
        try {
            LocalDate oggi = LocalDate.now();

            List<Prestito> prestitiScadutiNonRestituiti = em.createQuery(
                            "SELECT p FROM Prestito p WHERE p.DataRestituzionePrevista < :oggi AND p.DataRestituzioneEffettiva IS NULL",
                            Prestito.class)
                    .setParameter("oggi", oggi)
                    .getResultList();

            if (prestitiScadutiNonRestituiti.isEmpty()) {
                System.out.println("Nessun prestito scaduto e non restituito trovato.");
            } else {
                System.out.println("Prestiti scaduti e non restituiti:");

                for (Prestito prestito : prestitiScadutiNonRestituiti) {
                    System.out.println("Prestito ID: " + prestito.getId() +
                            " - Prestito: " + prestito.getElementoPrestato() +
                            " - Data Scadenza: " + prestito.getDataRestituzionePrevista() +
                            " - Data Prestito: " + prestito.getDataInizioPrestito());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " <----Errore durante la ricerca");
        }
    }


}
