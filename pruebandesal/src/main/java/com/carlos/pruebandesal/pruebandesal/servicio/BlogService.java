package com.carlos.pruebandesal.pruebandesal.servicio;

import java.util.List;
import com.carlos.pruebandesal.pruebandesal.entidades.Blog;
import javax.ejb.Local;

/**
 *
 * @author Carlos
 */
@Local
public interface BlogService {

    public List<Blog> listarBlogs() throws Exception;
    
    public Blog obtenerBlogPorId(Long id) throws Exception;
   
    public void agregarBlog(Blog blog) throws Exception;
    
    public void actualizarBlog(Blog blog) throws Exception;
    
    public void eliminarBlog(Blog blog) throws Exception;
}
