package com.example.uzbmap.payload;

import com.example.uzbmap.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GmDto {
    private Integer addressId;
    private String corpName,directorName;
}
