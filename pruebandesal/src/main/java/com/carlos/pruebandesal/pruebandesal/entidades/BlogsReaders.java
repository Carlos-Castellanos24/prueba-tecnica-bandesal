package com.carlos.pruebandesal.pruebandesal.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "blogs_readers")
@Data
@IdClass(BlogsReadersId.class)
public class BlogsReaders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "r_id")
    private Reader reader;
 
    @Id
    @ManyToOne
    @JoinColumn(name = "b_id", updatable = true)
    private Blog blog;

}
