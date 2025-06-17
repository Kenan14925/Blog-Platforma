
package blog.platforma.repository;

import java.util.List;

import blog.platforma.model.Korisnik;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class KorisnikRepository {

    @PersistenceContext
    EntityManager em;

    public List<Korisnik> findAll() {
        return em.createNamedQuery(Korisnik.GET_ALL, Korisnik.class).getResultList();
    }

    public Korisnik findById(Long id) {
        return em.find(Korisnik.class, id);
    }

    public Korisnik findByUsername(String username) {
        List<Korisnik> lista = em.createNamedQuery(Korisnik.GET_BY_USERNAME, Korisnik.class)
                .setParameter("username", username)
                .getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    public void save(Korisnik korisnik) {
        if (korisnik.getId() == null) {
            em.persist(korisnik);
        } else {
            em.merge(korisnik);
        }
    }

    public void delete(Long id) {
        Korisnik k = findById(id);
        if (k != null) {
            em.remove(k);
        }
    }
}

