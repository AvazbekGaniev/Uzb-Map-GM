package com.example.uzbmap.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private List<Integer> carIds;
    private Integer addressId;
}
