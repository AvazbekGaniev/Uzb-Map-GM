package com.example.uzbmap.controller;

import com.example.uzbmap.entity.*;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.payload.UserDto;
import com.example.uzbmap.repository.AddressRepository;
import com.example.uzbmap.repository.CarRepository;
import com.example.uzbmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public ApiResponse save(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());

        Optional<Address> optionalAddress = addressRepository.findById(userDto.getAddressId());
        if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
        user.setAddress(optionalAddress.get());

        List<Car> carList = carRepository.findAllById(userDto.getCarIds());
        user.setCares(carList);
        userRepository.save(user);
        return new ApiResponse("Succeed!", true);
    }


    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Car> getCarByUserId(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        List<Integer> integers = new ArrayList<>();
        for (Car care : user.getCares()) {
            integers.add(care.getId());
        }
        return userRepository.byUserId(integers);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody UserDto userDto) {

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
        User user = userOptional.get();

        if (!userDto.getName().isEmpty()) user.setName(userDto.getName());

        if (userDto.getAddressId() != null) {
            Optional<Address> optionalAddress = addressRepository.findById(userDto.getAddressId());
            if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
            user.setAddress(optionalAddress.get());
        }
        if (userDto.getCarIds() != null) {
            List<Car> carList = carRepository.findAllById(userDto.getCarIds());
            user.setCares(carList);
        }
        userRepository.save(user);
        return new ApiResponse("Succeed!", true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        if (userRepository.existsById(id)) return new ApiResponse("Data Is Incorrect!", false);
        userRepository.deleteById(id);
        return new ApiResponse("Succeed!", true);
    }
}
