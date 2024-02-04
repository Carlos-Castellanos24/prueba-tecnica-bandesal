package com.carlos.pruebandesal.pruebandesal.dao;

import java.util.List;
import com.carlos.pruebandesal.pruebandesal.entidades.Reader;

/**
 *
 * @author Carlos
 */
public interface ReaderDao {
    
    public List<Reader> listarReaders() throws Exception;
    
    public Reader obtenerReaderPorId(Long id) throws Exception;
    
    public void agregarReader(Reader reader) throws Exception;
    
    public void actualizarReader(Reader reader) throws Exception;
    
    public void eliminarReader(Reader reader) throws Exception;
}
