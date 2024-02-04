package com.carlos.pruebandesal.pruebandesal.servicio;

import javax.ejb.Stateless;
import javax.inject.Inject;
import com.carlos.pruebandesal.pruebandesal.dao.BlogReadersDao;
import com.carlos.pruebandesal.pruebandesal.entidades.BlogsReaders;
import java.util.List;

/**
 *
 * @author Carlos
 */
@Stateless
public class BlogsReadersServiceImpl implements BlogsReadersService {

    @Inject
    private BlogReadersDao blogReadersDao;

    @Override
    public List<BlogsReaders> listarBlogsReaders() throws Exception {
        try {
            return blogReadersDao.listarBlogsReaders();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void agregarBlogReaders(BlogsReaders blogReaders) throws Exception {
        try {
            if (blogReaders != null) {
                blogReadersDao.agregarBlogReaders(blogReaders);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarBlogReaders(BlogsReaders blogReaders) throws Exception {
        try {
            if (blogReaders != null) {
                blogReadersDao.actualizarBlogReaders(blogReaders);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarBlogReaders(Long id) throws Exception {
        try {
            if (id != null) {
                blogReadersDao.eliminarBlogReaders(id);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public BlogsReaders obtenerBlogReadersPorId(Long id) throws Exception {
        try {
            if (id != null) {
                return blogReadersDao.obtenerBlogReadersPorId(id);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
