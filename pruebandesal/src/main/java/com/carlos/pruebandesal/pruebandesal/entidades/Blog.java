package com.carlos.pruebandesal.pruebandesal.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Carlos
 */
@Entity
@Data
@Table(name = "blogs")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "title")
    private String title;

    @Size(max = 4000)
    @Column(name = "description")
    private String description;
}
