package com.carlos.pruebandesal.pruebandesal.controlador;

import com.carlos.pruebandesal.pruebandesal.entidades.Login;
import com.carlos.pruebandesal.pruebandesal.exceptions.UsuarioNoEncontradoException;
import com.carlos.pruebandesal.pruebandesal.servicio.LoginService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos
 */
@Data
@SessionScoped
@Named("loginController")
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LoginService loginService;

    private String usuario;

    private String password;

    private String confirmarPassword;

    private String nombreUsuario;

    public String login() {
        try {
            Login usuarioLogin = loginService.validarInicioSesion(usuario, password);

            if (usuarioLogin != null) {
                this.nombreUsuario = usuarioLogin.getUsuario();
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("nombreUsuario", this.nombreUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Bienvenido " + session.getAttribute("nombreUsuario").toString()));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

                return "/content/index?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales incorrectas", "Credenciales incorrectas"));
            }
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error", e.getMessage()));
        } finally {
            usuario = null;
            password = null;
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/login?faces-redirect=true";
    }

    public String registrar() {
        try {
            if (!usuario.equals("") && !password.equals("")) {
                if (password == null ? confirmarPassword != null : !password.equals(confirmarPassword)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden"));
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                    return "/registro?faces-redirect=true";
                }
                Login login = new Login();
                login.setUsuario(usuario);
                login.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                loginService.guardarLogin(login);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con exito", "Ya puede iniciar sesión"));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
            return "/login?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            usuario = null;
            password = null;
        }
        return "/registro?faces-redirect=true";
    }

    public String cerrarSesion() {
        HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (sesion != null) {
            sesion.invalidate();
        }
        return "/login?faces-redirect=true";
    }
}
