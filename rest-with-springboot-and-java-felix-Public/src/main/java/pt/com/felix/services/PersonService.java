package pt.com.felix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.com.felix.exceptions.ResourceNotFoundException;
import pt.com.felix.models.Person;
import pt.com.felix.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public Person create(Person person){
        logger.info("Creating one person");
        return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one Person");
      Person entity =  personRepository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

      entity.setFirstName(person.getFirstName());
      entity.setLastName(person.getLastName());
      entity.setAge(person.getAge());
      entity.setGender(person.getGender());
      entity.setAddress(person.getAddress());

        return personRepository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person with id: " + id);
       Person entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        personRepository.deleteById(id);
    }

    public List<Person> findByAll(){
        logger.info("FindAll:");
        /*
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 10; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
         */
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding person with id: " + id);
/*
     Person person = new Person();
     person.setId(counter.incrementAndGet());
     person.setFirstName("Felix");
     person.setLastName("Felipe");
     person.setAge(25);
     person.setGender("Male");
     return person;
    }

 */
        return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
    }
    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Felix");
        person.setLastName("Felipe");
        person.setAge(25);
        return person;
    }


}
