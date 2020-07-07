package com.pet.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pet.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	Optional<User> findByUserNameAndPassword(String userName, String password);

}
