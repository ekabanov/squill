package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.samples.petclinic.Pet;
import org.springframework.stereotype.Service;


@Service 
public class MemoryClinic {
  public Collection<Pet> getPets() {
    List<Pet> pets = new ArrayList<Pet>();
    
    Pet pet = new Pet();
    pet.setName("Lassie");
    pets.add(pet);
    
    pet = new Pet();
    pet.setName("Rex");
    pets.add(pet);
    
    pet = new Pet();
    pet.setName("Dino");
    pets.add(pet);
  
    return pets;
  }
}
