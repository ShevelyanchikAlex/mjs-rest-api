package com.epam.esm.repository;

import com.epam.esm.domain.User;

public interface UserRepository extends CrudRepository<User>, CounterRepository {
}