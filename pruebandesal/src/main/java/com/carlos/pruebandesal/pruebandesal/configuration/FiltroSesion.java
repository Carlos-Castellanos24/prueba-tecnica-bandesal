package com.carlos.pruebandesal.pruebandesal.configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebFilter("/content/**")
public class FiltroSesion implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sr;
        HttpServletResponse httpServletResponse = (HttpServletResponse) sr1;
        
        HttpSession sesion = httpServletRequest.getSession(false);
        
        boolean isAuth = (sesion != null && sesion.getAttribute("nombreUsuario") != null);
        
        if (isAuth) {
            fc.doFilter(sr, sr1);
        }else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.xhtml");
        }
    }   
}
