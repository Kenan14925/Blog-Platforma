
package blog.platforma.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = Komentar.GET_BY_POST_ID, query = "SELECT k FROM Komentar k WHERE k.blogPost.id = :postId")
})
public class Komentar {

    public static final String GET_BY_POST_ID = "Komentar.getByPostId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "komentar_seq")
    private Long id;

    private String content;

    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Korisnik author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private BlogPost blogPost;

    public Komentar() {}

    public Komentar(String content, Date createdAt, Korisnik author, BlogPost blogPost) {
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.blogPost = blogPost;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Korisnik getAuthor() { return author; }
    public void setAuthor(Korisnik author) { this.author = author; }

    public BlogPost getBlogPost() { return blogPost; }
    public void setBlogPost(BlogPost blogPost) { this.blogPost = blogPost; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Komentar other = (Komentar) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "Komentar [id=" + id + ", content=" + content + "]";
    }
}

