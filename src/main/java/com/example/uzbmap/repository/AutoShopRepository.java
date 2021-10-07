package com.example.uzbmap.repository;


import com.example.uzbmap.entity.AutoShop;
import com.example.uzbmap.entity.Car;
import com.example.uzbmap.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoShopRepository extends JpaRepository<AutoShop,Integer> {
    List<AutoShop> findAllByGmId(Integer id);
    @Query("select c from Car c where c.id in (select cars from AutoShop where id = ?1)")
    List<Car> byAutoShopId(Integer autoShopId);


}
