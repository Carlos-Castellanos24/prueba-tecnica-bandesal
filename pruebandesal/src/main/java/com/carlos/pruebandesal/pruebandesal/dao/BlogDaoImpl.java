package com.carlos.pruebandesal.pruebandesal.dao;

import com.carlos.pruebandesal.pruebandesal.entidades.Blog;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos
 */
@Stateless
public class BlogDaoImpl implements BlogDao {

    @PersistenceContext(unitName = "bandesalPU")
    EntityManager em;

    @Override
    public List<Blog> listarBlogs() throws Exception {
        try {
            return em.createQuery("SELECT b FROM Blog b", Blog.class).getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void agregarBlog(Blog blog) throws Exception {
        try {
            if (blog != null) {
                em.persist(blog);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarBlog(Blog blog) throws Exception {
        try {
            if (blog != null) {
                em.merge(blog);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarBlog(Blog blog) throws Exception {
        try {
            if (blog != null) {
                em.remove(em.merge(blog));
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Blog obtenerBlogPorId(Long id) throws Exception {
        try {
            if (id != null) {
                return em.find(Blog.class, id);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
