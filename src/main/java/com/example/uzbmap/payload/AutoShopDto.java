package com.example.uzbmap.payload;

import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.Car;
import com.example.uzbmap.entity.Gm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoShopDto {

    private String name;

    private Integer gmId, addressId;

    List<Integer> carIds;
}
