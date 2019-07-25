package fi.academy.jpaworld;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Country implements Serializable {

    @Id
    private String code;
    private String name;
    private Integer population;
    private String headOfState;
    private String continent;
    private String region;
    private Integer surfacearea;
    private String localname;
    private String governmentform;
    private String code2;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital")
    private City capital;
    @OneToMany(mappedBy = "country")
    private List<City> cities;





    public Country() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(Integer surfacearea) {
        this.surfacearea = surfacearea;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }

    public String getGovernmentform() {
        return governmentform;
    }

    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", headOfState='" + headOfState + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", surfacearea=" + surfacearea +
                ", localname='" + localname + '\'' +
                ", governmentform='" + governmentform + '\'' +
                ", code2='" + code2 + '\'' +
                '}';
    }
}
