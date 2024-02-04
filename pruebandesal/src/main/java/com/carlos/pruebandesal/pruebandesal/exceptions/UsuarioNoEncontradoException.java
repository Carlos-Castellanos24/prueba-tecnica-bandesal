package com.carlos.pruebandesal.pruebandesal.exceptions;
/**
 *
 * @author Carlos
 */
public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException() {
        super();
    }

    public UsuarioNoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioNoEncontradoException(Throwable cause) {
        super(cause);
    }
}
