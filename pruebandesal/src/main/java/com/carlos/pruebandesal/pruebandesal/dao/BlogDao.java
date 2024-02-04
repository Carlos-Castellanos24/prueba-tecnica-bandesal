package com.carlos.pruebandesal.pruebandesal.dao;

import java.util.List;
import com.carlos.pruebandesal.pruebandesal.entidades.Blog;

/**
 *
 * @author Carlos
 */
public interface BlogDao {
    
    public List<Blog> listarBlogs() throws Exception;
    
    public Blog obtenerBlogPorId(Long id) throws Exception;
    
    public void agregarBlog(Blog blog) throws Exception;
    
    public void actualizarBlog(Blog blog) throws Exception;
    
    public void eliminarBlog(Blog blog) throws Exception;
}
