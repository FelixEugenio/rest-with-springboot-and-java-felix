package pt.com.felix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.com.felix.data.dto.PersonDTO;
import pt.com.felix.services.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    // senao colocar o @Service na classe de personService teriamos que escrever o codigo assim
    // private PersonDTOService personService = new PersonDTOService();

    /*
    @RequestMapping(
            value = "/person/{id}"
            ,method = RequestMethod.GET
            ,produces = MediaType.APPLICATION_JSON_VALUE
    )
     */
    @GetMapping("/person/{id}")
   public PersonDTO findById(@PathVariable("id") Long id){
       return personService.findById(id);
   }

   /*
   @RequestMapping(
           value = "/person"
           ,method = RequestMethod.GET
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )

    */
    @GetMapping("/person")
   public List<PersonDTO> findByAll(){
        return personService.findByAll();
   }
/*
   @RequestMapping(
           value = "/person"
           ,method = RequestMethod.POST
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )

 */
    @PostMapping("/person")
   public PersonDTO create(@RequestBody PersonDTO person){
      return personService.create(person);
   }

   /*
   @RequestMapping(
           value = "/person/{id}"
           ,method = RequestMethod.PUT
           ,produces = MediaType.APPLICATION_JSON_VALUE
   )
    */
    @PutMapping("/person/{id}")
   public PersonDTO update(@RequestBody PersonDTO person){
        return personService.update(person);
   }

   /*
    @RequestMapping(
            value = "/person/{id}"
            ,method = RequestMethod.DELETE)

    */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
         personService.delete(id);
         return ResponseEntity.noContent().build();
    }

}
