
package blog.platforma.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = BlogPost.GET_ALL_POSTS, query = "SELECT p FROM BlogPost p"),
        @NamedQuery(name = BlogPost.GET_POSTS_BY_TITLE, query = "SELECT p FROM BlogPost p WHERE p.title LIKE :title")
})
public class BlogPost {

    public static final String GET_ALL_POSTS = "BlogPost.getAllPosts";
    public static final String GET_POSTS_BY_TITLE = "BlogPost.getPostsByTitle";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogpost_seq")
    private Long id;

    private String title;

    private String content;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Korisnik author;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Komentar> komentari = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public BlogPost() {}

    public BlogPost(String title, String content, Date createdAt, Korisnik author) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Korisnik getAuthor() { return author; }
    public void setAuthor(Korisnik author) { this.author = author; }

    public Set<Komentar> getKomentari() { return komentari; }
    public void setKomentari(Set<Komentar> komentari) { this.komentari = komentari; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        BlogPost other = (BlogPost) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "BlogPost [id=" + id + ", title=" + title + "]";
    }
}

