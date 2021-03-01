package jpabook.jshop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hello {

    @Id @GeneratedValue
    private Long id;

    private String Test;

    public Long getId() {
        return this.id;
    }

    public String getTest() {
        return this.Test;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTest(String Test) {
        this.Test = Test;
    }
}
