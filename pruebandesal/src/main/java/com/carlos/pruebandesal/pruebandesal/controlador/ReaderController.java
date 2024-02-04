package com.carlos.pruebandesal.pruebandesal.controlador;

import com.carlos.pruebandesal.pruebandesal.servicio.ReaderService;
import com.carlos.pruebandesal.pruebandesal.entidades.Reader;
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
@Named("readerController")
@ViewScoped
@Data
public class ReaderController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ReaderService readerService;

    private List<Reader> readers;

    private Reader reader;

    private Reader readerSeleccionado;

    private Reader readerEdit;

    @PostConstruct
    public void init() {
        try {
            this.readers = readerService.listarReaders();
            this.reader = new Reader();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        }
    }

    public void agregarReader() {
        try {
            this.reader.setId(CustomUtils.generarIds());
            this.readerService.agregarReader(reader);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reader agregado con éxito", null));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", ex.getMessage()));
        }
        this.readers.add(reader);
        this.init();
    }

    public void eliminarReader() {
        try {
            if (readerSeleccionado != null) {
                readerService.eliminarReader(readerSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reader eliminado con éxito", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El reader seleccionado, está vacio"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            readerSeleccionado = null;
            this.init();
        }
    }

    public void datosReaderUpdate() {
        if (this.readerSeleccionado != null) {
            this.readerEdit = readerSeleccionado;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El reader seleccionado, está vacio"));
        }
    }

    public void editarReader() {
        try {
            if (readerEdit != null) {
                readerService.actualizarReader(readerEdit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reader actualizado con éxito", null));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Objeto vacio", "El reader seleccionado para editar, está vacio"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", e.getMessage()));
        } finally {
            readerEdit = null;
            this.init();
        }
    }
}
