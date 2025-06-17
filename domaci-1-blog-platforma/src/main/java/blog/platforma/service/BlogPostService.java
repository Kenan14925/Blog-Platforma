
package blog.platforma.service;

import java.util.List;

import blog.platforma.exception.BlogException;
import blog.platforma.model.BlogPost;
import blog.platforma.repository.BlogPostRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BlogPostService {

    @Inject
    BlogPostRepository postRepo;

    public List<BlogPost> getAll() {
        return postRepo.findAll();
    }

    public BlogPost getById(Long id) {
        BlogPost p = postRepo.findById(id);
        if (p == null) {
            throw new BlogException("Blog post sa id " + id + " ne postoji");
        }
        return p;
    }

    @Transactional
    public void create(BlogPost post) {
        postRepo.save(post);
    }

    @Transactional
    public void update(Long id, BlogPost post) {
        BlogPost existing = getById(id);
        existing.setTitle(post.getTitle());
        existing.setContent(post.getContent());
        existing.setCreatedAt(post.getCreatedAt());
        existing.setAuthor(post.getAuthor());
        existing.setTags(post.getTags());
        postRepo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        postRepo.delete(id);
    }
}

