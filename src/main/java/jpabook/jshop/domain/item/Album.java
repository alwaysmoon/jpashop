package jpabook.jshop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    public String getArtist() {
        return this.artist;
    }

    public String getEtc() {
        return this.etc;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
