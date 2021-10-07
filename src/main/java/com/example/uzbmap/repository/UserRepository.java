package com.example.uzbmap.repository;


import com.example.uzbmap.entity.Car;
import com.example.uzbmap.entity.District;
import com.example.uzbmap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select c from Car c where c.id in ?1")
    List<Car> byUserId(List<Integer> id);
}
