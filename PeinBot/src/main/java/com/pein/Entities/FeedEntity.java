package com.pein.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Feed.findCategory", query = "SELECT f FROM FeedEntity f where f.category= :category"),
        @NamedQuery(name = "Feed.findId", query = "SELECT f FROM FeedEntity f where f.id= :id")
})
@Table(name = "feed", schema = "public", catalog = "Pein")
public class FeedEntity {
    private long id;
    private String name;
    private String link;
    private String category;

    @Id
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

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedEntity that = (FeedEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(link, that.link) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link, category);
    }
}
