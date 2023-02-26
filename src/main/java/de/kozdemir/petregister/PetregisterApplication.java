package de.kozdemir.petregister;

import de.kozdemir.petregister.model.Owner;
import de.kozdemir.petregister.model.Pet;
import de.kozdemir.petregister.model.Status;
import de.kozdemir.petregister.model.Type;
import de.kozdemir.petregister.repository.OwnerRepository;
import de.kozdemir.petregister.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor // für finale Felder
public class PetregisterApplication implements CommandLineRunner {

    // Werden üder eine Constructor-Injection zugewiesen, daher kein @Autowired nötig
    private final PetRepository petRepository;

    private final OwnerRepository ownerRepository;

    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(PetregisterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*
		var owner = Owner.builder()
								.firstname("Peter")
								.lastname("Parker")
								.email("p.parker@shield.org")
								.password(passwordEncoder.encode("geheim#123"))
								.build();

//		ownerRepository.save(owner);

		var pet = Pet.builder()
								.birthDate(LocalDate.of(2020, 1, 1))
								.chipId("1234567890")
								.name("Bello")
								.type(Type.DOG)
								.status(Status.OK)
								.race("Dackel")
								.owner(owner)
								.build();

		petRepository.save(pet);

        // owner.addPet(pet); // statt owner.getPets().add(pet)
        // ownerRepository.save(owner);
*/
    }
}
