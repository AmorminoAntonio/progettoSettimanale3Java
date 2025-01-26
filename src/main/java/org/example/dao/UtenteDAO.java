package org.example.dao;

import org.example.entities.Utente;

import javax.persistence.EntityManager;


public class UtenteDAO {

    EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiUtente(Utente u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public Utente getById(Long id) {
        return em.find(Utente.class, id);
    }

    public void delete(Utente u) {
        em.getTransaction().begin();
        em.remove(u);
    }

}
