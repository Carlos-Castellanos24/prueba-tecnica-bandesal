package com.carlos.pruebandesal.pruebandesal.dao;

import com.carlos.pruebandesal.pruebandesal.entidades.BlogsReaders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos
 */
@Stateless
public class BlogReadersDaoImpl implements BlogReadersDao {

    @PersistenceContext(unitName = "bandesalPU")
    EntityManager em;

    @Override
    public List<BlogsReaders> listarBlogsReaders() throws Exception {
        try {
            return em.createQuery("SELECT br FROM BlogsReaders br").getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void agregarBlogReaders(BlogsReaders blogReaders) throws Exception {
        try {
            if (blogReaders != null) {
                em.persist(blogReaders);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarBlogReaders(BlogsReaders blogReaders) throws Exception {
        try {
            if (blogReaders != null) {
                em.merge(blogReaders);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarBlogReaders(Long id) throws Exception {
        try {
            if (id != null) {
                em.createQuery("DELETE FROM BlogsReaders br WHERE br.blog.id = :blogId")
                        .setParameter("blogId", id)
                        .executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public BlogsReaders obtenerBlogReadersPorId(Long id) throws Exception {
        try {
            if (id != null) {
                return em.find(BlogsReaders.class, id);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
