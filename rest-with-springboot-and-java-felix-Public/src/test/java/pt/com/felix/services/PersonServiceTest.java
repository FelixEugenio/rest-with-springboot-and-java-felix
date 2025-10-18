package pt.com.felix.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.felix.models.Person;
import pt.com.felix.repositories.PersonRepository;
import pt.com.felix.unitests.mapper.mocks.MockPerson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)

class PersonServiceTest {

    MockPerson input;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByAll() {
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        var result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().equals("self")
        && link.getHref().equals("http://localhost:8080/person/1")
        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().equals("findaAll")
                && link.getHref().equals("http://localhost:8080/person")
                && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().equals("create")
                && link.getHref().equals("http://localhost:8080/person")
                && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().equals("update")
                && link.getHref().equals("http://localhost:8080/person")
                && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().equals("delete")
                && link.getHref().equals("http://localhost:8080/person/1")
                && link.getType().equals("DELETE")));

        assertEquals("Address Test", result.getAddress());
        assertEquals("First Name Test", result.getFirstName());
        assertEquals("Last Name Test", result.getLastName());
        assertEquals(25, result.getAge());
        assertEquals("Male", result.getGender());
    }
}