package com.example.integratewithmultidatasource.repository;

import com.example.integratewithmultidatasource.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
