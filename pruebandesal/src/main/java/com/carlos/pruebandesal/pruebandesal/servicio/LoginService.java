package com.carlos.pruebandesal.pruebandesal.servicio;

import com.carlos.pruebandesal.pruebandesal.entidades.Login;

/**
 *
 * @author Carlos
 */
public interface LoginService {

    public Login validarInicioSesion(String usuario, String password) throws Exception;
    
    public void guardarLogin(Login login)throws Exception;
}
