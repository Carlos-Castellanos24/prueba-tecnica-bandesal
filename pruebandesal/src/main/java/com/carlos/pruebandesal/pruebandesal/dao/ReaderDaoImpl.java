package com.carlos.pruebandesal.pruebandesal.dao;

import com.carlos.pruebandesal.pruebandesal.entidades.Reader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos
 */
@Stateless
public class ReaderDaoImpl implements ReaderDao {

    @PersistenceContext(unitName = "bandesalPU")
    EntityManager em;

    @Override
    public List<Reader> listarReaders() throws Exception {
        try {
            return em.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    @Override
    public void agregarReader(Reader reader) throws Exception {
        try {
            if (reader != null) {
                em.persist(reader);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void actualizarReader(Reader reader) throws Exception {
        try {
            if (reader != null) {
                em.merge(reader);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void eliminarReader(Reader reader) throws Exception {
        try {
            if (reader != null) {
                em.remove(em.merge(reader));
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Reader obtenerReaderPorId(Long id) throws Exception {
        try {
            if (id != null) {
                return em.find(Reader.class, id);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
