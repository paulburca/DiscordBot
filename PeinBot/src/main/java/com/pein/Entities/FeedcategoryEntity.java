package com.pein.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name="FeedCategory.findId2", query="SELECT f FROM FeedcategoryEntity f where f.idCategory= :id")
@Table(name = "feedcategory", schema = "public", catalog = "Pein")
@IdClass(FeedcategoryEntityPK.class)
public class FeedcategoryEntity {
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
        FeedcategoryEntity that = (FeedcategoryEntity) o;
        return idFeed == that.idFeed && idCategory == that.idCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFeed, idCategory);
    }
}
