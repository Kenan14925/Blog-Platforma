
package blog.platforma.service;

import java.util.List;

import blog.platforma.exception.BlogException;
import blog.platforma.model.Korisnik;
import blog.platforma.repository.KorisnikRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KorisnikService {

    @Inject
    KorisnikRepository korisnikRepo;

    public List<Korisnik> getAll() {
        return korisnikRepo.findAll();
    }

    public Korisnik getById(Long id) {
        Korisnik k = korisnikRepo.findById(id);
        if (k == null) {
            throw new BlogException("Korisnik sa id " + id + " ne postoji");
        }
        return k;
    }

    @Transactional
    public void create(Korisnik korisnik) {
        korisnikRepo.save(korisnik);
    }

    @Transactional
    public void update(Long id, Korisnik korisnik) {
        Korisnik existing = getById(id);
        existing.setUsername(korisnik.getUsername());
        existing.setPassword(korisnik.getPassword());
        existing.setEmail(korisnik.getEmail());
        existing.setRole(korisnik.getRole());
        korisnikRepo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        korisnikRepo.delete(id);
    }
}

