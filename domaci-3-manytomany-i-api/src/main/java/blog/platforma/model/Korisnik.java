
package blog.platforma.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = Korisnik.GET_ALL, query = "SELECT k FROM Korisnik k"),
        @NamedQuery(name = Korisnik.GET_BY_USERNAME, query = "SELECT k FROM Korisnik k WHERE k.username = :username")
})
public class Korisnik {

    public static final String GET_ALL = "Korisnik.getAll";
    public static final String GET_BY_USERNAME = "Korisnik.getByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_seq")
    private Long id;

    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "korisnik_rola",
            joinColumns = @JoinColumn(name = "korisnik_id"),
            inverseJoinColumns = @JoinColumn(name = "rola_id"))
    private Set<Rola> role = new HashSet<>();

    public Korisnik() {}

    public Korisnik(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Rola> getRole() { return role; }
    public void setRole(Set<Rola> role) { this.role = role; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Korisnik other = (Korisnik) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Korisnik [id=" + id + ", username=" + username + ", email=" + email + "]";
    }
}
