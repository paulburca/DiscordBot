package com.pein.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="Category.findName", query="SELECT c FROM CategoryEntity c where c.name= :name"),
        @NamedQuery(name="Category.findId", query="SELECT c FROM CategoryEntity c where c.id= :id"),
        @NamedQuery(name="Category.findAll", query="SELECT c FROM CategoryEntity c")
})
@Table(name = "category", schema = "public", catalog = "Pein")
public class CategoryEntity {
    private long id;
    private String name;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
