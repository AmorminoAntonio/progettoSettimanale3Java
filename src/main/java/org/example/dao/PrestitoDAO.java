package org.example.dao;

import org.example.entities.Prestito;


import javax.persistence.EntityManager;

public class PrestitoDAO {

    EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

}
