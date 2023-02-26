package de.kozdemir.petregister.repository;

import de.kozdemir.petregister.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
