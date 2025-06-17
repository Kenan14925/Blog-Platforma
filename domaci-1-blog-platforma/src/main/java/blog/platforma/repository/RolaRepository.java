
package blog.platforma.repository;

import java.util.List;

import blog.platforma.model.Rola;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class RolaRepository {

    @PersistenceContext
    EntityManager em;

    public List<Rola> findAll() {
        return em.createQuery("SELECT r FROM Rola r", Rola.class).getResultList();
    }

    public Rola findById(Long id) {
        return em.find(Rola.class, id);
    }

    public void save(Rola rola) {
        if (rola.getId() == null) {
            em.persist(rola);
        } else {
            em.merge(rola);
        }
    }

    public void delete(Long id) {
        Rola r = findById(id);
        if (r != null) {
            em.remove(r);
        }
    }
}

