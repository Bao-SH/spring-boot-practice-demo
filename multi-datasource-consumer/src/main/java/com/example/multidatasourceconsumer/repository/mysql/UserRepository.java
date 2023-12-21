package com.example.multidatasourceconsumer.repository.mysql;

import com.example.multidatasourceconsumer.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
