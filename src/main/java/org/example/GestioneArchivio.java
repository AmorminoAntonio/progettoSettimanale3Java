package org.example;

import org.example.dao.ArchivioDAO;
import org.example.entities.Catalogo;
import org.example.entities.Libro;
import org.example.entities.Rivista;
import org.example.enumerated.TipoGenere;
import org.example.enumerated.TipoPeriodicità;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GestioneArchivio {


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pr2Jpaql");
    private static final EntityManager em = emf.createEntityManager();


    public static void main(String[] args) {

        ArchivioDAO archivioDao = new ArchivioDAO(em);

        Catalogo l1 = new Libro("la bella e la bestia", 2015, 120, "cristian l'autore", TipoGenere.ROMANZO);
        Catalogo r1 = new Rivista("TV sorridi a gattoni", 1925, 25, TipoPeriodicità.SETTIMANALE);

        archivioDao.save(l1);
        archivioDao.save(r1);


        em.close();
        emf.close();

    }
}
