package com.example.uzbmap.repository;


import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.Gm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GmRepository extends JpaRepository<Gm,Integer> {


}
