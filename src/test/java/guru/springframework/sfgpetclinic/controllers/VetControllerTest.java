package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.fauxspring.ModelImpl;
import guru.springframework.sfgpetclinic.ControllersTest;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;

public class VetControllerTest implements ControllersTest {
    VetController controller;
    SpecialtyService specialityService;
    VetService vetService;

    @BeforeEach
    void setUp() {
        specialityService=new SpecialityMapService();
        vetService= new VetMapService(specialityService);
        vetService.save(new Vet(1L, "jef", "Hoeybergs",
                new HashSet<>(Arrays.asList(new Speciality(1L, "Cows"), new Speciality(2L, "Horses")))));
        vetService.save(new Vet(2L, "Jos", "Vermeulen",
                new HashSet<>(Arrays.asList(new Speciality(3L, "Goats")))));
        controller = new VetController(vetService);
    }

    @Test
    void testListVets() {
        Model model = new ModelImpl();
        HashSet<Vet> vets = new HashSet<>(Arrays.asList(new Vet(1L, "jef", "Hoeybergs",
                new HashSet<>(Arrays.asList(new Speciality(1L, "Cows"), new Speciality(2L, "Horses")))),
                new Vet(2L, "Jos", "Vermeulen",
                        new HashSet<>(Arrays.asList(new Speciality(3L, "Goats"))))));
        assertTrue(controller.listVets(model).equals("vets/index"));
        controller.listVets(model);
        assertEquals( vets,((ModelImpl)model).getVetAttributes().get("vets"));

    }
}
