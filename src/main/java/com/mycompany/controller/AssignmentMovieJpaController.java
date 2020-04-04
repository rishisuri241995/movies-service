/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.controller.exceptions.PreexistingEntityException;
import com.mycompany.models.AssignmentMovie;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rishi
 */
public class AssignmentMovieJpaController implements Serializable {

    public AssignmentMovieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AssignmentMovie assignmentMovie) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(assignmentMovie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAssignmentMovie(assignmentMovie.getId()) != null) {
                throw new PreexistingEntityException("AssignmentMovie " + assignmentMovie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AssignmentMovie assignmentMovie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            assignmentMovie = em.merge(assignmentMovie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = assignmentMovie.getId();
                if (findAssignmentMovie(id) == null) {
                    throw new NonexistentEntityException("The assignmentMovie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AssignmentMovie assignmentMovie;
            try {
                assignmentMovie = em.getReference(AssignmentMovie.class, id);
                assignmentMovie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assignmentMovie with id " + id + " no longer exists.", enfe);
            }
            em.remove(assignmentMovie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AssignmentMovie> findAssignmentMovieEntities() {
        return findAssignmentMovieEntities(true, -1, -1);
    }

    public List<AssignmentMovie> findAssignmentMovieEntities(int maxResults, int firstResult) {
        return findAssignmentMovieEntities(false, maxResults, firstResult);
    }

    private List<AssignmentMovie> findAssignmentMovieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AssignmentMovie.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AssignmentMovie findAssignmentMovie(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AssignmentMovie.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssignmentMovieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AssignmentMovie> rt = cq.from(AssignmentMovie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
