package com.carlos.pruebandesal.pruebandesal.servicio;

import com.carlos.pruebandesal.pruebandesal.dao.LoginDao;
import com.carlos.pruebandesal.pruebandesal.entidades.Login;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos
 */
@Stateless
public class LoginServiceImpl implements LoginService {

    @Inject
    private LoginDao loginDao;

    @Override
    public Login validarInicioSesion(String usuario, String password) throws Exception {
        try {
            if (!usuario.equals("") && !password.equals("")) {               
                return loginDao.validarInicioSesion(usuario, password);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void guardarLogin(Login login) throws Exception {
        try {
            if (login != null) {
                loginDao.guardarLogin(login);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
