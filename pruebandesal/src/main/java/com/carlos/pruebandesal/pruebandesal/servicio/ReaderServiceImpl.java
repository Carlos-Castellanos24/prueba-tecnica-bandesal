package com.carlos.pruebandesal.pruebandesal.servicio;

import com.carlos.pruebandesal.pruebandesal.entidades.Reader;
import com.carlos.pruebandesal.pruebandesal.dao.ReaderDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Carlos
 */
@Stateless
public class ReaderServiceImpl implements ReaderService{

    @Inject
    private ReaderDao readerDao;
    
    @Override
    public List<Reader> listarReaders() throws Exception {
        try{
            return readerDao.listarReaders();
        }catch(Exception e){
            throw new Exception(e);
        }
    }   
    
    @Override
    public void agregarReader(Reader reader) throws Exception {
        try{
            if (reader != null) {
                readerDao.agregarReader(reader);
            }
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarReader(Reader reader) throws Exception {
        try {
            if (reader != null) {
                readerDao.actualizarReader(reader);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarReader(Reader reader) throws Exception {
        try {
            if (reader != null) {
                readerDao.eliminarReader(reader);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Reader obtenerReaderPorId(Long id) throws Exception {
        try {
            if (id != null) {
                return readerDao.obtenerReaderPorId(id);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
