package com.carlos.pruebandesal.pruebandesal.dao;

import java.util.List;
import com.carlos.pruebandesal.pruebandesal.entidades.BlogsReaders;

/**
 *
 * @author Carlos
 */
public interface BlogReadersDao {
    
    public List<BlogsReaders> listarBlogsReaders() throws Exception;
    
    public BlogsReaders obtenerBlogReadersPorId(Long id) throws Exception;
    
    public void agregarBlogReaders(BlogsReaders blogReaders) throws Exception;
    
    public void actualizarBlogReaders(BlogsReaders blogReaders) throws Exception;
    
    public void eliminarBlogReaders(Long id) throws Exception;
}
