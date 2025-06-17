
package blog.platforma.service;

import java.util.List;

import blog.platforma.exception.BlogException;
import blog.platforma.model.Rola;
import blog.platforma.repository.RolaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RolaService {

    @Inject
    RolaRepository rolaRepo;

    public List<Rola> getAll() {
        return rolaRepo.findAll();
    }

    public Rola getById(Long id) {
        Rola r = rolaRepo.findById(id);
        if (r == null) {
            throw new BlogException("Rola sa id " + id + " ne postoji");
        }
        return r;
    }

    @Transactional
    public void create(Rola rola) {
        rolaRepo.save(rola);
    }

    @Transactional
    public void update(Long id, Rola rola) {
        Rola existing = getById(id);
        existing.setNaziv(rola.getNaziv());
        rolaRepo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        rolaRepo.delete(id);
    }
}

