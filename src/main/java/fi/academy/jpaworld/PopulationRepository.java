package fi.academy.jpaworld;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "countriestoo", path = "countriestoo")
public interface PopulationRepository extends CrudRepository<MaaPopulation, String> {

    List<Country> findByCode (@Param("haku") String haku);
}
