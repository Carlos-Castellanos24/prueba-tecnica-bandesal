package com.carlos.pruebandesal.pruebandesal.dao;

import com.carlos.pruebandesal.pruebandesal.entidades.Login;

/**
 *
 * @author Carlos
 */

public interface LoginDao {
 
    public Login validarInicioSesion(String usuario, String password)throws Exception;
    
    public void guardarLogin(Login login)throws Exception;
}
