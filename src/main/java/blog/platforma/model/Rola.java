
package blog.platforma.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Rola {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rola_seq")
    private Long id;

    private String naziv;

    @ManyToMany(mappedBy = "role")
    private Set<Korisnik> korisnici = new HashSet<>();

    public Rola() {}

    public Rola(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Set<Korisnik> getKorisnici() { return korisnici; }
    public void setKorisnici(Set<Korisnik> korisnici) { this.korisnici = korisnici; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Rola other = (Rola) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Rola [id=" + id + ", naziv=" + naziv + "]";
    }
}

