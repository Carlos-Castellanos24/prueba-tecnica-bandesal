package com.carlos.pruebandesal.pruebandesal.controlador;

import com.carlos.pruebandesal.pruebandesal.entidades.Blog;
import com.carlos.pruebandesal.pruebandesal.entidades.BlogsReaders;
import com.carlos.pruebandesal.pruebandesal.entidades.Reader;
import com.carlos.pruebandesal.pruebandesal.servicio.BlogService;
import com.carlos.pruebandesal.pruebandesal.servicio.BlogsReadersService;
import com.carlos.pruebandesal.pruebandesal.servicio.ReaderService;
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
@Data
@ViewScoped
@Named("blogsReadersController")
public class BlogsReadersController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BlogsReadersService blogsReadersService;

    @Inject
    private BlogService blogService;

    @Inject
    private ReaderService readerService;

    private List<BlogsReaders> blogsReaders;

    private List<Reader> readersList;

    private List<Blog> blogsList;

    private BlogsReaders blogReaderSeleccionado;

    private BlogsReaders blogReadersObj;

    private Long selectedReaderUpdate;

    private Long selectedBlogUpdate;

    @PostConstruct
    public void init() {
        try {
            this.blogReaderSeleccionado = null;
            this.selectedReaderUpdate = null;
            this.selectedBlogUpdate = null;
            this.blogReadersObj = new BlogsReaders();

            this.blogsReaders = blogsReadersService.listarBlogsReaders();

            this.readersList = readerService.listarReaders();
            this.blogsList = blogService.listarBlogs();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        }
    }

    public void agregarBlogReader() {
        try {
            Long readerId = obtenerReaderIdInsertDialog();
            Long blogId = obtenerBlogIdInsertDialog();

            Reader readerObtenido = readerService.obtenerReaderPorId(readerId);
            Blog blogObtenido = blogService.obtenerBlogPorId(blogId);

            if (readerObtenido != null && blogObtenido != null) {
                this.blogReadersObj.setBlog(blogObtenido);
                this.blogReadersObj.setReader(readerObtenido);

                blogsReadersService.agregarBlogReaders(blogReadersObj);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog Reader agregado con éxito", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No encontrado", "No se encontraron datos"));
                return;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        }
        this.blogsReaders.add(this.blogReadersObj);
        this.init();
    }

    public void eliminarBlogReader() {
        try {
            if (blogReaderSeleccionado != null) {
                blogsReadersService.eliminarBlogReaders(blogReaderSeleccionado.getBlog().getId());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog Reader eliminado con éxito", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El blog reader seleccionado, está vacio"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            this.blogReaderSeleccionado = null;
            this.init();
        }
    }

    public void datosEditBlogsReaders() {
        if (blogReaderSeleccionado != null) {
            Reader selectedReader = blogReaderSeleccionado.getReader();
            Blog selectedBlog = blogReaderSeleccionado.getBlog();
            setSelectedReaderUpdate(selectedReader.getId());
            setSelectedBlogUpdate(selectedBlog.getId());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El blog reader seleccionado, está vacio"));
        }
    }

    public void editarBlogReader() {
        try {
            Reader readerObtenido = readerService.obtenerReaderPorId(obtenerReaderIdUpdateDialog());
            Blog blogObtenido = blogService.obtenerBlogPorId(obtenerBlogIdUpdateDialog());

            if (readerObtenido != null && blogObtenido != null) {
                BlogsReaders updatedBlogReader = new BlogsReaders();
                updatedBlogReader.setBlog(blogObtenido);
                updatedBlogReader.setReader(readerObtenido);

                blogsReadersService.agregarBlogReaders(updatedBlogReader);

                blogsReadersService.eliminarBlogReaders(blogReaderSeleccionado.getBlog().getId());

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blog Reader actualizado con éxito", null));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No encontrado", "No se encontraron datos"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            this.blogReaderSeleccionado = null;
            this.init();
        }
    }

    private Long obtenerReaderIdInsertDialog() {
        String readerIdString = (String) FacesContext.getCurrentInstance().getViewRoot().findComponent("dialogReaderForm:readersGroup").getAttributes().get("value");
        return readerIdString != null ? Long.parseLong(readerIdString) : null;
    }

    private Long obtenerBlogIdInsertDialog() {
        String blogIdString = (String) FacesContext.getCurrentInstance().getViewRoot().findComponent("dialogReaderForm:blogsGroup").getAttributes().get("value");
        return blogIdString != null ? Long.parseLong(blogIdString) : null;
    }

    private Long obtenerReaderIdUpdateDialog() {
        Long readerId = (Long) FacesContext.getCurrentInstance().getViewRoot().findComponent("blogReaderFormUpdateDlg:readersGroup").getAttributes().get("value");
        return readerId != null ? readerId : null;
    }

    private Long obtenerBlogIdUpdateDialog() {
        Long blogId = (Long) FacesContext.getCurrentInstance().getViewRoot().findComponent("blogReaderFormUpdateDlg:blogsGroup").getAttributes().get("value");
        return blogId != null ? blogId : null;
    }
}
