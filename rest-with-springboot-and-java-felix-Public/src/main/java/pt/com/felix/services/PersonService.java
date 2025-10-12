package pt.com.felix.services;

import org.springframework.stereotype.Service;
import pt.com.felix.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findByAll(){
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 10; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return (Person) persons;
    }

    public Person findById(String id){
     logger.info("Finding person with id: " + id);

     Person person = new Person();
     person.setId(counter.incrementAndGet());
     person.setFirstName("Felix");
     person.setLastName("Felipe");
     person.setAge(25);
     person.setGender("Male");
     return person;
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
