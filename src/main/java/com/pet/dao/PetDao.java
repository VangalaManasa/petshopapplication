package com.pet.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pet.model.Pet;

@Repository
public interface PetDao extends CrudRepository<Pet, Integer> {
	
	@Query(value = "select * from pet p where p.pet_name like %:petName%",nativeQuery = true)
	Optional<List<Pet>> getPetByPetName(String petName);
	
	
	@Query(value = "select p.pet_price from pet p where p.pet_id=:petId",nativeQuery = true)
    double findPetPriceByPetId(@Param("petId") int petId);
	
	@Query(value = "select p.pet_name from pet p where p.pet_id=:petId",nativeQuery = true)
    String findPetNameByPetId(@Param("petId") int petId);
	

}
