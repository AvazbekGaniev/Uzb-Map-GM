package com.example.uzbmap.repository;

import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    @Query("select d from District d where d.region = ?1")
    List<District> byRegId(Integer DistId);

}
