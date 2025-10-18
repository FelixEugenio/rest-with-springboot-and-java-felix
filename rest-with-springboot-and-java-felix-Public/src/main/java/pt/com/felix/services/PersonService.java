package pt.com.felix.services;

import org.springframework.beans.factory.annotation.Autowired;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import pt.com.felix.Mapper.ObjectMapper;
import pt.com.felix.Mapper.custom.PersonMapper;
import pt.com.felix.controllers.PersonController;
import pt.com.felix.data.dto.v2.PersonDTOV2;
import pt.com.felix.exceptions.ResourceNotFoundException;
import pt.com.felix.data.dto.v1.PersonDTO;
import pt.com.felix.models.Person;
import pt.com.felix.repositories.PersonRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
     PersonMapper converter;

    private AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person");
        var entity = ObjectMapper.parseObject(person,Person.class);
        var dto = ObjectMapper.parseObject(personRepository.save(entity),PersonDTO.class);
        addHateOsLinks(dto.getId(), dto);
        return dto;
    }

    public PersonDTOV2 create(PersonDTOV2 person){
        logger.info("Creating one person");
        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one PersonDTO");
      Person entity =  personRepository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

      entity.setFirstName(person.getFirstName());
      entity.setLastName(person.getLastName());
      entity.setAge(person.getAge());
      entity.setGender(person.getGender());
      entity.setAddress(person.getAddress());

        return ObjectMapper.parseObject(personRepository.save(entity),PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one person with id: " + id);
       Person entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        personRepository.deleteById(id);
    }

    public List<PersonDTO> findByAll(){
        logger.info("FindAll:");
        /*
        List<PersonDTO> persons = new ArrayList<PersonDTO>();
        for(int i = 0; i < 10; i++){
            PersonDTO person = mockPersonDTO(i);
            persons.add(person);
        }
        return persons;
         */
        return ObjectMapper.parseListObjects(personRepository.findAll(),PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding person with id: " + id);
/*
     PersonDTO person = new PersonDTO();
     person.setId(counter.incrementAndGet());
     person.setFirstName("Felix");
     person.setLastName("Felipe");
     person.setAge(25);
     person.setGender("Male");
     return person;
    }

 */
              var entity =  personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
              var dto = ObjectMapper.parseObject(entity,PersonDTO.class);
        addHateOsLinks(id, dto);
        return dto;
    }

    private static void addHateOsLinks(Long id, PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).findByAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
    }

    private PersonDTO mockPersonDTO(int i){
        PersonDTO person = new PersonDTO();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Felix");
        person.setLastName("Felipe");
        person.setAge(25);
        return person;
    }
}
