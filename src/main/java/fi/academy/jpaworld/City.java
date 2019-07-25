package fi.academy.jpaworld;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToOne(mappedBy = "city")
    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private Country country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
