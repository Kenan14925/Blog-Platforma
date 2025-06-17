
package blog.platforma.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    private Long id;

    private String naziv;

    @ManyToMany(mappedBy = "tags")
    private Set<BlogPost> posts = new HashSet<>();

    public Tag() {}

    public Tag(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Set<BlogPost> getPosts() { return posts; }
    public void setPosts(Set<BlogPost> posts) { this.posts = posts; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Tag other = (Tag) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", naziv=" + naziv + "]";
    }
}
