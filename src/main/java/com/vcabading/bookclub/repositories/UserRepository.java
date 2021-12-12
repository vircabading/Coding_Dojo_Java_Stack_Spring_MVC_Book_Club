package com.vcabading.bookclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vcabading.bookclub.models.User;

/////////////////////////////////////////////////////////////
//	USER REPOSITORY
/////////////////////////////////////////////////////////////

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUserName(String userName);
}
