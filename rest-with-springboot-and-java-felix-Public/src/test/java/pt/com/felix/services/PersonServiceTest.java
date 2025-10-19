
package pt.com.felix.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.com.felix.data.dto.v1.PersonDTO;
import pt.com.felix.exceptions.ResourceNotFoundException;
import pt.com.felix.models.Person;
import pt.com.felix.repositories.PersonRepository;
import pt.com.felix.unitests.mapper.mocks.MockPerson;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static final String BASE_URL = "http://localhost:8080/person";

    MockPerson input;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
    }

    @Test
    void whenCreateThenReturnPersonDTO() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(personRepository.save(any(Person.class))).thenReturn(persisted);

        var result = personService.create(dto);

        assertNotNull(result);
        assertPersonDTOFields(result);
        verifyHATEOASLinks(result);
    }

    @Test
    void whenUpdateThenReturnUpdatedPersonDTO() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        Person persisted = person;

        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(persisted);

        var result = personService.update(dto);

        assertNotNull(result);
        assertPersonDTOFields(result);
        verifyHATEOASLinks(result);
    }

    @Test
    void whenUpdateWithInvalidIdThenThrowResourceNotFoundException() {
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.update(dto));
    }

    @Test
    void whenDeleteThenSuccess() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(personRepository).deleteById(1L);

        personService.delete(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenDeleteWithInvalidIdThenThrowResourceNotFoundException() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.delete(1L));
    }

    @Test
    void whenFindAllThenReturnPersonDTOList() {
        List<Person> persons = List.of(input.mockEntity(1), input.mockEntity(2));
        when(personRepository.findAll()).thenReturn(persons);

        var result = personService.findByAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        for (PersonDTO dto : result) {
            assertPersonDTOBasicFields(dto);
        }
    }

    @Test
    void whenFindByIdThenReturnPersonDTO() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = personService.findById(1L);

        assertNotNull(result);
        assertPersonDTOFields(result);
        verifyHATEOASLinks(result);
    }

    @Test
    void whenFindByIdWithInvalidIdThenThrowResourceNotFoundException() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.findById(1L));
    }

    private void assertPersonDTOFields(PersonDTO result) {
        assertPersonDTOBasicFields(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
    }

    private void assertPersonDTOBasicFields(PersonDTO result) {
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals(25, result.getAge());
        assertEquals("Male", result.getGender());
    }

    private void verifyHATEOASLinks(PersonDTO result) {
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().equals("self") &&
                        link.getHref().equals(BASE_URL + "/1") &&
                        link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().equals("findAll") &&
                        link.getHref().equals(BASE_URL) &&
                        link.getType().equals("GET")));

        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().equals("create") &&
                        link.getHref().equals(BASE_URL) &&
                        link.getType().equals("POST")));

        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().equals("update") &&
                        link.getHref().equals(BASE_URL) &&
                        link.getType().equals("PUT")));

        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getRel().equals("delete") &&
                        link.getHref().equals(BASE_URL + "/1") &&
                        link.getType().equals("DELETE")));
    }
}