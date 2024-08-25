package guru.springframework.sfgpetclinic.services.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@DisplayName("Owner map service test - ")
public class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    PetService petService;
    PetTypeService petTypeService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
        System.out.println("first before each");
    }

    @DisplayName("Verify zero owners")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type -")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
            System.out.println("Nested before each");
        }

        @Test
        void testPetCount(){
            int petTypeCount= petTypeService.findAll().size();
            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save owners tests -")
        @Nested
        class SaveOwnersTests{
             
            @BeforeEach
            void setUp(){
                ownerMapService.save(new Owner(1L, "Before", "each"));
                System.out.println("saved owners before each");
            }

            @Test
            void savedOwner(){
                Owner owner= new Owner(2L, "Joe", "Buck");             
                Owner savedOwner= ownerMapService.save(owner);
                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("find owners test -")
            @Nested
            class FindOwnerstests{

                @DisplayName("find owner")
                @Test
                void findOwner(){
                    Owner foundOwner=ownerMapService.findById(1L);
                    assertThat(foundOwner).isNotNull();
                }

                @DisplayName("find owner not found")
                @Test
                void findOwnerNotFound(){
                     Owner foundOwner=ownerMapService.findById(2L);
                     assertThat(foundOwner).isNull();
                }
            }

        }
    }

    @DisplayName("verify still zero owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }
}
