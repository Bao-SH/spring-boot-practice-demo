package com.example.integratewithmultidatasource.repository.snowflake;

import com.example.integratewithmultidatasource.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SnowflakeRepository extends JpaRepository<ProductEntity, UUID>{
//    @Modifying
//    @Query(nativeQuery = true, value = "create or replace table demo(c1 string)")
//    void create();
//
//    @Modifying
//    @Query(nativeQuery = true, value = "insert into demo values ('hello world')")
//    void insert();
//
//    @Query(nativeQuery = true, value = "select * from demo")
//    String fetch();
}
