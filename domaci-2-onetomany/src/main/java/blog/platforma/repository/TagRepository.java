
package blog.platforma.repository;

import java.util.List;

import blog.platforma.model.Tag;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class TagRepository {

    @PersistenceContext
    EntityManager em;

    public List<Tag> findAll() {
        return em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    public Tag findById(Long id) {
        return em.find(Tag.class, id);
    }

    public void save(Tag tag) {
        if (tag.getId() == null) {
            em.persist(tag);
        } else {
            em.merge(tag);
        }
    }

    public void delete(Long id) {
        Tag t = findById(id);
        if (t != null) {
            em.remove(t);
        }
    }
}

