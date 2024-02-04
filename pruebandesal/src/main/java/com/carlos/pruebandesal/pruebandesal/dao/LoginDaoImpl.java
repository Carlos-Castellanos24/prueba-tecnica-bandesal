package com.carlos.pruebandesal.pruebandesal.dao;

import com.carlos.pruebandesal.pruebandesal.entidades.Login;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos
 */
@Stateless
public class LoginDaoImpl implements LoginDao {

    @PersistenceContext(unitName = "bandesalPU")
    EntityManager em;

    @Override
    public Login validarInicioSesion(String usuario, String password) throws Exception {
        try {
            Login usuarioLogin = em.createQuery("SELECT l FROM Login l WHERE l.usuario = :usuario", Login.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();

            if (usuarioLogin != null && BCrypt.checkpw(password, usuarioLogin.getPassword())) {
                return usuarioLogin;
            } else {
                return null;
            }
        } catch (NoResultException e) {
            throw new Exception("No se encontro ningun usuario");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void guardarLogin(Login login) throws Exception {
        try {
            if (login != null) {
                em.persist(login);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
