package com.pet.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pet.model.Purchase;
@Repository
public interface PurchaseDao extends CrudRepository<Purchase, Integer>{

	Optional<List<Purchase>> findAllByUserId(int userId);

}
