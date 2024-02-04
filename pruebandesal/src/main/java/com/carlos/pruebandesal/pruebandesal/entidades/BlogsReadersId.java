package com.carlos.pruebandesal.pruebandesal.entidades;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

/**
 *
 * @author Carlos
 */

@Data
public class BlogsReadersId implements Serializable {

    private Long reader;
    private Long blog;
    
    @Override
    public int hashCode() {
        return Objects.hash(reader, blog);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlogsReadersId that = (BlogsReadersId) obj;
        return Objects.equals(reader, that.reader) &&
               Objects.equals(blog, that.blog);
    }
}
