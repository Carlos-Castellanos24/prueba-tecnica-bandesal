package com.carlos.pruebandesal.pruebandesal.servicio;

import com.carlos.pruebandesal.pruebandesal.dao.BlogDao;
import com.carlos.pruebandesal.pruebandesal.entidades.Blog;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Carlos
 */
@Stateless
public class BlogServiceImpl implements BlogService{

    @Inject
    private BlogDao blogDao;
    
    @Override
    public List<Blog> listarBlogs() throws Exception {
        try{
            return blogDao.listarBlogs();
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public void agregarBlog(Blog blog) throws Exception {
        try{
            if (blog != null) {
                blogDao.agregarBlog(blog);
            }
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarBlog(Blog blog) throws Exception {
        try {
            if (blog != null) {
                blogDao.actualizarBlog(blog);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarBlog(Blog blog) throws Exception {
        try {
            if (blog != null) {
                blogDao.eliminarBlog(blog);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Blog obtenerBlogPorId(Long id) throws Exception {
        try{
            if (id != null) {
                return blogDao.obtenerBlogPorId(id);
            }else{
                return null;
            }
        }catch(Exception e){
            throw new Exception(e);
        }
    }
}
