package edu.abl.poty.repositories;

import edu.abl.poty.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findPersonsByCountry(String country);

    List<Person> findPersonsByCategory(String country);

}
