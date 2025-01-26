package org.example;

import com.github.javafaker.Faker;
import org.example.dao.ArchivioDAO;
import org.example.dao.PrestitoDAO;
import org.example.dao.UtenteDAO;
import org.example.entities.*;
import org.example.enumerated.TipoGenere;
import org.example.enumerated.TipoPeriodicità;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.*;


public class GestioneArchivio {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettoSettimanale3");
    private static final EntityManager em = emf.createEntityManager();
    private static Faker faker = new Faker(Locale.ITALY);
    private static Set<Libro> libri = new HashSet<>();
    private static Set<Rivista> riviste = new HashSet<>();
    private static Set<Utente> utenti = new HashSet<>();
    private static Set<Catalogo> catalogo = new HashSet<>();


    public static void main(String[] args) {

        ArchivioDAO archivioDao = new ArchivioDAO(em);
        UtenteDAO utenteDao = new UtenteDAO(em);
        PrestitoDAO prestitoDao = new PrestitoDAO(em);


        // UTENTE
        Utente user = new Utente(faker.name().firstName(), faker.name().lastName(), LocalDate.of(1996, 6, 8), faker.number().digits(10));
        utenti.add(user);
        //utenteDao.aggiungiUtente(user);


        catalogo.addAll(libri);
        catalogo.addAll(riviste);


        // PRESTITO
        Set<Catalogo> elementoPrestato = new HashSet<>();

        LocalDate dataInizioPrestito = LocalDate.of(2024, 11, 2);


//        elementoPrestato.add(archivioDao.ricercaPerISBN(5L));
//        elementoPrestato.add(archivioDao.ricercaPerISBN(6L));
//        elementoPrestato.add(archivioDao.ricercaPerISBN(7L));
//        elementoPrestato.add(archivioDao.ricercaPerISBN(8L));
//        elementoPrestato.add(archivioDao.ricercaPerISBN(9L));


        Prestito p = new Prestito(utenteDao.getById(12L), elementoPrestato, dataInizioPrestito, dataInizioPrestito.plusDays(30), LocalDate.of(2025, 1, 24));
        //prestitoDao.aggiungiPrestito(p);


        // LANCIO dei metodi per query.

        // Qui sto aggiungendo un elemento ... (1)

        // LIBRI
        Libro libro = new Libro(faker.book().title(), faker.number().numberBetween(1825, 2025), faker.number().numberBetween(30, 60), faker.book().author(), TipoGenere.MANGA);
        libri.add(libro);


        // RIVISTE
        Rivista rivista = new Rivista(faker.book().title(), faker.number().numberBetween(1984, 2024), faker.number().numberBetween(18, 38), TipoPeriodicità.SETTIMANALE);
        riviste.add(rivista);

        // archivioDao.aggiungiElementoCatalogo(libro);
        // archivioDao.aggiungiElementoCatalogo(rivista);

        //--------------------------------------------------

        // Qui elimino un elemento dal catalogo invece ... (2)

        // archivioDao.rimuoviElementoPerISBN(10L);

        //---------------------------------------------------

        // Qui sto ricercando un elemento tramite il codice univoco ... (3)

        // archivioDao.ricercaPerISBN(11L);


        //----------------------------------------------------

        // Qui ricerco per l'anno di pubblicazione... (4)

        // archivioDao.ricercaPerAnnoPubblicazione(2007);

        //-----------------------------------------------------

        // Qui

        archivioDao.ricercaPerAutore("Ivano");

















       /* user.getElementiPresiInPrestito().add(p);
        Utente prestitiPerUser = utenteDao.getById(3L);

        for (Prestito prestito : prestitiPerUser.getElementiPresiInPrestito()) {
            System.out.println(prestito);
        }*/


        em.close();
        emf.close();

    }
}
