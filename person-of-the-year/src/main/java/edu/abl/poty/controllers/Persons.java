package edu.abl.poty.controllers;

import edu.abl.poty.models.Person;
import edu.abl.poty.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Persons {

    @Autowired
    PersonRepository persons;

    // ------------------ GET ----------------- //
    @GetMapping("/persons")
    public Iterable<Person> getAllPersons() {
        return persons.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return persons.findById(id).get();
    }

    @GetMapping("/persons/search/") //todo fix this
    public List<Person> getPersonsByParams(
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "category", required = false) String category) {
        if (country != null) {
            return persons.findPersonsByCountry(country);
        }
        if (category != null) {
            return persons.findPersonsByCountry(category);
        }
        return null;
    }


    // ------------------ POST ----------------- //
    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person newPerson) {
        return persons.save(newPerson);
    }

    // ------------------ PUT/PATCH ----------------- //
    @PutMapping("/persons/{id}")
    public Person replacePersonByID(@PathVariable Long id, @RequestBody Person newPerson) {
        if (persons.existsById(id)) {
            newPerson.setId(id);
            return persons.save(newPerson);
        } else {
            return null;
        }
    }

    @PatchMapping("/persons/{id}")
    public String updateGalleryByID(@PathVariable Long id, @RequestBody Person personToUpdateWith) {
        return persons.findById(id).map(foundPerson -> {
            if (personToUpdateWith.getName() != null) foundPerson.setName(personToUpdateWith.getName());
            if (personToUpdateWith.getTitle() != null) foundPerson.setTitle(personToUpdateWith.getTitle());
            if (personToUpdateWith.getYear() != 0) foundPerson.setYear(personToUpdateWith.getYear());
            if (personToUpdateWith.getHonor() != null) foundPerson.setHonor(personToUpdateWith.getHonor());
            if (personToUpdateWith.getCountry() != null) foundPerson.setCountry(personToUpdateWith.getCountry());
            if (personToUpdateWith.getBirthYear() != 0) foundPerson.setBirthYear(personToUpdateWith.getBirthYear());
            if (personToUpdateWith.getDeathYear() != 0) foundPerson.setDeathYear(personToUpdateWith.getDeathYear());
            if (personToUpdateWith.getCategory() != null) foundPerson.setCategory(personToUpdateWith.getCategory());
            if (personToUpdateWith.getContext() != null) foundPerson.setContext(personToUpdateWith.getContext());

            persons.save(foundPerson);
            return "person saved";
        }).orElse("person not found");
    }

    // ------------------ DELETE ----------------- //
    @DeleteMapping("/persons/all")
    public void deleteAllPersons() {
        persons.deleteAll();
    }

    @DeleteMapping("/persons/{id}")
    public void deletePersonByID(@PathVariable Long id) {
        persons.deleteById(id);
    }


}
