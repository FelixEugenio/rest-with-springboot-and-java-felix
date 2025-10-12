package pt.com.felix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.com.felix.models.Person;
import pt.com.felix.services.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    // senao colocar o @Service na classe de personService teriamos que escrever o codigo assim
    // private PersonService personService = new PersonService();

    @RequestMapping(
            value = "/person/{id}"
            ,method = RequestMethod.GET
            ,produces = MediaType.APPLICATION_JSON_VALUE
    )
   public Person findById(@PathVariable("id") String id){
       return personService.findById(id);
   }


   @RequestMapping(
           value = "/person"
           ,method = RequestMethod.GET
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )
   public List<Person> findByAll(){
        return personService.findByAll();
   }

   @RequestMapping(
           value = "/person"
           ,method = RequestMethod.POST
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )
   public Person create(@RequestBody Person person){
      return personService.create(person);
   }

   @RequestMapping(
           value = "/person/{id}"
           ,method = RequestMethod.PUT
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )
   public Person update(@RequestBody Person person){
        return personService.update(person);
   }

    @RequestMapping(
            value = "/person/{id}"
            ,method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
         personService.delete(id);
    }

}
