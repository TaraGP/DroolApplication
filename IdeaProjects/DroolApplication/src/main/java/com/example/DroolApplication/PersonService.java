package com.example.DroolApplication;

import com.example.DroolApplication.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DroolApplication.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final KieContainer kieContainer;

    public PersonService(PersonRepository personRepository, KieContainer kieContainer) {
        this.personRepository = personRepository;
        this.kieContainer = kieContainer;
    }

    public Person getEligibitiyCheck(Person person) {
        KieSession kieSession = kieContainer.newKieSession();
        try {
            kieSession.insert(person);
            kieSession.fireAllRules();
            // Update the eligibleforcar field with the calculated value
            boolean eligibleForCar = person.isEligibleForCar();
            person.setEligibleForCar(eligibleForCar);

            // Save the updated person entity
            personRepository.save(person);
        } finally {
            kieSession.dispose();
        }
        return person;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    public void updatePerson(Integer id, Person updatedPerson) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            personRepository.save(person);
        }
    }
}



