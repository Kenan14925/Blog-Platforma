
package blog.platforma.repository;

import java.util.List;

import blog.platforma.model.Komentar;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class KomentarRepository {

    @PersistenceContext
    EntityManager em;

    public List<Komentar> findByPostId(Long postId) {
        return em.createNamedQuery(Komentar.GET_BY_POST_ID, Komentar.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    public Komentar findById(Long id) {
        return em.find(Komentar.class, id);
    }

    public void save(Komentar komentar) {
        if (komentar.getId() == null) {
            em.persist(komentar);
        } else {
            em.merge(komentar);
        }
    }

    public void delete(Long id) {
        Komentar k = findById(id);
        if (k != null) {
            em.remove(k);
        }
    }
}

