package com.carlos.pruebandesal.pruebandesal.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Carlos
 */
@Entity
@Data
@Table(name = "login")
public class Login implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "usuario")
    @Size(max = 15, min = 1)
    private String usuario;
    
    @Column(name = "pw")
    @Size(max = 500, min = 1)
    private String password;
}
