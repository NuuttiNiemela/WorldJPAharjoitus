package fi.academy.jpaworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class MaailmaController {
    private CountryRepository cr;
    private PopulationRepository pr;

    @Autowired
    public MaailmaController(CountryRepository rep, PopulationRepository prep) {
        this.cr = rep;
        this.pr = prep;
    }

    @GetMapping("/country")
    public Iterable<Country> haeKaikki() {
        return cr.findAll();
    }

    @GetMapping("/country/{name}")
    public Iterable<Country> haeMaat(@PathVariable("name") String name) {
        cr.findByNameContainsIgnoreCase(name);

        return cr.findByNameContainsIgnoreCase(name);
    }

    @GetMapping("/country/luku/{luku}")
    public Iterable<Country> haetAsukasluvulla(@PathVariable String luku) {
        return cr.findByPopulationGreaterThanEqual(Integer.parseInt(luku));
    }

    @PatchMapping("/country/{code}")
    public ResponseEntity<Country> paivitaAsukasluku(@PathVariable("code") String code, @RequestBody MaaPopulation country) {
        country.setCode(code);
        pr.save(country);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/country")
    public ResponseEntity<Country> lisaaMaa(@RequestBody Country uusi, UriComponentsBuilder builder) {
        List<Country> lista = cr.findByCode(uusi.getCode());
        if (lista.size() > 0) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/country/{code}").buildAndExpand(uusi.getCode()).toUri());
            cr.save(uusi);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/country/{code}")
    public ResponseEntity<Void> poistaMaa(@PathVariable("code") String code) {
        List<Country> lista = cr.findByCode(code);
        if (lista.size()>0) {
            cr.delete(lista.get(0));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/maat/{name}")
    public Iterable<Country> haeNimella(@PathVariable("name") String name) {
        return cr.findByNameAndOrderByPopulation(name);
    }

    @GetMapping("/maat")
    public Iterable<Country> haeKaikkiSivuittain(@RequestParam int page, int size) {
       Pageable p = PageRequest.of(page,size, Sort.Direction.ASC, "population");
        return cr.findAll(p);
    }

}
