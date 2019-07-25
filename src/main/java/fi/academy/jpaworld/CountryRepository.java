package fi.academy.jpaworld;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends CrudRepository<Country, String> {

    List<Country> findByNameContainsIgnoreCase (@Param("haku") String haku);
    List<Country> findByPopulationGreaterThanEqual(int luku);
    @Query("Select c FROM Country c WHERE c.code = :haku")
    List<Country> findByCode (@Param("haku") String haku);

    @Query("Select c FROM Country c WHERE c.name = :nimi")
    List<Country> findByNameAndOrderByPopulation(@Param("nimi") String nimi);


}
