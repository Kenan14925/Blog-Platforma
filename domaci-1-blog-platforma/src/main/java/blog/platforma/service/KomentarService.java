
package blog.platforma.service;

import java.util.List;

import blog.platforma.exception.BlogException;
import blog.platforma.model.Komentar;
import blog.platforma.repository.KomentarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KomentarService {

    @Inject
    KomentarRepository komentarRepo;

    public List<Komentar> getByPostId(Long postId) {
        return komentarRepo.findByPostId(postId);
    }

    public Komentar getById(Long id) {
        Komentar k = komentarRepo.findById(id);
        if (k == null) {
            throw new BlogException("Komentar sa id " + id + " ne postoji");
        }
        return k;
    }

    @Transactional
    public void create(Komentar komentar) {
        komentarRepo.save(komentar);
    }

    @Transactional
    public void update(Long id, Komentar komentar) {
        Komentar existing = getById(id);
        existing.setContent(komentar.getContent());
        existing.setCreatedAt(komentar.getCreatedAt());
        existing.setAuthor(komentar.getAuthor());
        existing.setBlogPost(komentar.getBlogPost());
        komentarRepo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        komentarRepo.delete(id);
    }
}

