
package blog.platforma.repository;

import java.util.List;

import blog.platforma.model.BlogPost;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class BlogPostRepository {

    @PersistenceContext
    EntityManager em;

    public List<BlogPost> findAll() {
        return em.createNamedQuery(BlogPost.GET_ALL_POSTS, BlogPost.class).getResultList();
    }

    public BlogPost findById(Long id) {
        return em.find(BlogPost.class, id);
    }

    public void save(BlogPost post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    public void delete(Long id) {
        BlogPost post = findById(id);
        if (post != null) {
            em.remove(post);
        }
    }
}
