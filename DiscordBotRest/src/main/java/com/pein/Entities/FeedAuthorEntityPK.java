package com.pein.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FeedAuthorEntityPK implements Serializable {
    private long idFeed;
    private long idAuthor;

    @Column(name = "id_feed")
    @Id
    public long getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(long idFeed) {
        this.idFeed = idFeed;
    }

    @Column(name = "id_author")
    @Id
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
        FeedAuthorEntityPK that = (FeedAuthorEntityPK) o;
        return idFeed == that.idFeed && idAuthor == that.idAuthor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFeed, idAuthor);
    }
}
