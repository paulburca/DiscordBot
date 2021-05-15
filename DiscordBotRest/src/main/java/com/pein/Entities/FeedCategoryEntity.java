package com.pein.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FeedCategory", schema = "public", catalog = "Pein")
@IdClass(FeedCategoryEntityPK.class)
public class FeedCategoryEntity {
    private long idFeed;
    private long idCategory;

    @Id
    @Column(name = "id_feed")
    public long getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(long idFeed) {
        this.idFeed = idFeed;
    }

    @Id
    @Column(name = "id_category")
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
        FeedCategoryEntity that = (FeedCategoryEntity) o;
        return idFeed == that.idFeed && idCategory == that.idCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFeed, idCategory);
    }
}
