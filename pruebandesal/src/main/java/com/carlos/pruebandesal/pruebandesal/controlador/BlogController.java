package com.carlos.pruebandesal.pruebandesal.controlador;

import com.carlos.pruebandesal.pruebandesal.servicio.BlogService;
import com.carlos.pruebandesal.pruebandesal.entidades.Blog;
import com.carlos.pruebandesal.pruebandesal.servicio.BlogsReadersService;
import com.carlos.pruebandesal.pruebandesal.servicio.ReaderService;
import com.carlos.pruebandesal.pruebandesal.utils.CustomUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author Carlos
 */
@Named("blogController")
@ViewScoped
@Data
public class BlogController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BlogService blogService;

    @Inject
    private ReaderService readerService;

    @Inject
    private BlogsReadersService blogsReadersService;

    private List<Blog> blogs;

    private Blog blog;

    private Blog blogSeleccionado;

    private Blog blogEdit;

    @PostConstruct
    public void init() {
        try {
            this.blogs = blogService.listarBlogs();
            this.blog = new Blog();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        }
    }

    public void agregarBlog() {
        try {
            this.blog.setId(CustomUtils.generarIds());
            this.blogService.agregarBlog(blog);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog agregado con éxito", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", ex.getMessage()));
        }
        this.blogs.add(blog);
        this.init();
    }

    public void eliminarBlog() {
        try {
            if (blogSeleccionado != null) {
                blogService.eliminarBlog(blogSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog eliminado con éxito", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El blog seleccionado, está vacio"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            blogSeleccionado = null;
            this.init();
        }
    }

    public void datosBlogUpdate() {
        if (blogSeleccionado != null) {
            this.blogEdit = blogSeleccionado;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El reader seleccionado, está vacio"));
        }
    }

    public void editarBlog() {
        try {
            if (blogEdit != null) {
                blogService.actualizarBlog(blogEdit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog actualizado con éxito", null));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El blog seleccionado a editar, está vacio"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            blogEdit = null;
            this.init();
        }
    }
}
