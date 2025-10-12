package pt.com.felix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.com.felix.models.Person;

public interface PersonRepository extends JpaRepository <Person, Long>{

}
