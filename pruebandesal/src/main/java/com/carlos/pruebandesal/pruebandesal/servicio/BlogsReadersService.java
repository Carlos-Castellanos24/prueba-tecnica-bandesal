package com.carlos.pruebandesal.pruebandesal.servicio;

import com.carlos.pruebandesal.pruebandesal.entidades.BlogsReaders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos
 */
@Local
public interface BlogsReadersService {
    
    public List<BlogsReaders> listarBlogsReaders() throws Exception;
    
    public BlogsReaders obtenerBlogReadersPorId(Long id) throws Exception;
    
    public void agregarBlogReaders(BlogsReaders blogReaders) throws Exception;
    
    public void actualizarBlogReaders(BlogsReaders blogReaders) throws Exception;
    
    public void eliminarBlogReaders(Long id) throws Exception;
}
