package com.example.uzbmap.repository;


import com.example.uzbmap.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query("select a from Address a where a.district = ?1")
    List<Address> byDistId(Integer DistId);


    List<Address> findAllByDistrictId(Integer id);
}
