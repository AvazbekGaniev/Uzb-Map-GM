package com.example.uzbmap.repository;


import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllById(List<Integer> idList);
    List<Car> findAllByRegionId(Integer id);
}
