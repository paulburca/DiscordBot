package com.pein.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="Feed.findName", query="SELECT f FROM FeedEntity f where f.name= :name"),
        @NamedQuery(name="Feed.findLink", query="SELECT f FROM FeedEntity f where f.link = :link"),
        @NamedQuery(name="Feed.findId", query="SELECT f FROM FeedEntity f where f.id= :id"),
        @NamedQuery(name="Feed.findAll", query="SELECT f FROM FeedEntity f")
})
@Table(name = "feed", schema = "public", catalog = "Pein")
public class FeedEntity {
    private long id;
    private String name;
    private String link;

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

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedEntity that = (FeedEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link);
    }
}
