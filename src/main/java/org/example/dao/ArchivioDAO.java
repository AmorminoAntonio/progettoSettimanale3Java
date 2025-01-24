package org.example.dao;

import org.example.entities.Catalogo;
import org.example.entities.Libro;
import org.example.entities.Rivista;

import javax.persistence.EntityManager;


public class ArchivioDAO {

    private EntityManager em;

    public ArchivioDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

}
