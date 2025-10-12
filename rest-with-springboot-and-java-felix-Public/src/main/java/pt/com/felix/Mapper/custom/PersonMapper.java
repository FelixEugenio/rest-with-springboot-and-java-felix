package pt.com.felix.Mapper.custom;
import org.springframework.stereotype.Service;
import pt.com.felix.data.dto.v1.PersonDTO;
import pt.com.felix.data.dto.v2.PersonDTOV2;
import pt.com.felix.models.Person;
import java.util.Date;

@Service
public class PersonMapper {
    public PersonDTOV2 convertToPersonDTOV2(Person person){
        PersonDTOV2 dto =  new PersonDTOV2();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAge(person.getAge());
        dto.setBirthDate(new Date());
        dto.setGender(person.getGender());
        dto.setAddress(person.getAddress());
        return  dto;
    }

    public Person convertEntityToDTO(PersonDTOV2 person){
        Person dto =  new Person();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAge(person.getAge());
        //dto.setBirthDate(new Date());
        dto.setGender(person.getGender());
        dto.setAddress(person.getAddress());
        return  dto;
    }

    public Person convertDTOtoEntity(PersonDTOV2 person){
        Person entity =  new Person();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAge(person.getAge());
        //dto.setBirthDate(new Date());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        return  entity;
    }
}
