
package blog.platforma.service;

import java.util.List;

import blog.platforma.exception.BlogException;
import blog.platforma.model.Tag;
import blog.platforma.repository.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TagService {

    @Inject
    TagRepository tagRepo;

    public List<Tag> getAll() {
        return tagRepo.findAll();
    }

    public Tag getById(Long id) {
        Tag t = tagRepo.findById(id);
        if (t == null) {
            throw new BlogException("Tag sa id " + id + " ne postoji");
        }
        return t;
    }

    @Transactional
    public void create(Tag tag) {
        tagRepo.save(tag);
    }

    @Transactional
    public void update(Long id, Tag tag) {
        Tag existing = getById(id);
        existing.setNaziv(tag.getNaziv());
        tagRepo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        tagRepo.delete(id);
    }
}

