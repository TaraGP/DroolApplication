package com.example.DroolApplication;

import com.example.DroolApplication.Person;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public Person getEligibility(@RequestBody Person person) {
        return personService.getEligibitiyCheck(person);
    }
    @GetMapping
    public List<Person> getAllPersons()
    {
        return personService.getAllPersons();
    }
    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id)
    {
        return personService.getPersonById(id);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id)
    {
        personService.deletePerson(id);
    }
    @PutMapping("/{id}")
    public void updatePerson(@PathVariable Integer id, @RequestBody Person person)
    {
        personService.getEligibitiyCheck(person);
        personService.updatePerson(id, person);
    }
}
