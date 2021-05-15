package com.pein.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FeedAuthor", schema = "public", catalog = "Pein")
@IdClass(FeedAuthorEntityPK.class)
public class FeedAuthorEntity {
    private long idFeed;
    private long idAuthor;

    @Id
    @Column(name = "id_feed")
    public long getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(long idFeed) {
        this.idFeed = idFeed;
    }

    @Id
    @Column(name = "id_author")
    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedAuthorEntity that = (FeedAuthorEntity) o;
        return idFeed == that.idFeed && idAuthor == that.idAuthor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFeed, idAuthor);
    }
}
