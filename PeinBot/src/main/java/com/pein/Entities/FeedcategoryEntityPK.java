package com.pein.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FeedcategoryEntityPK implements Serializable {
    private long idFeed;
    private long idCategory;

    @Column(name = "id_feed")
    @Id
    public long getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(long idFeed) {
        this.idFeed = idFeed;
    }

    @Column(name = "id_category")
    @Id
    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedcategoryEntityPK that = (FeedcategoryEntityPK) o;
        return idFeed == that.idFeed && idCategory == that.idCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFeed, idCategory);
    }
}
