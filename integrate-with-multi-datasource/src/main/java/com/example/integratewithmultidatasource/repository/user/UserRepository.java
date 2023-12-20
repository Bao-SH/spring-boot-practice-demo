package com.example.integratewithmultidatasource.repository.user;

import com.example.integratewithmultidatasource.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
