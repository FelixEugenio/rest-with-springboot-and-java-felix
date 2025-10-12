package pt.com.felix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.com.felix.models.Person;
import pt.com.felix.services.PersonService;

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

}
