package com.example.uzbmap.controller;

import com.example.uzbmap.entity.Car;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @PostMapping
    public ApiResponse save(@RequestBody Car carReq){
        Car car = new Car();
        car.setModel(carReq.getModel());
        car.setPrice(carReq.getPrice());
        car.setYear(carReq.getYear());
        carRepository.save(car);
        return new ApiResponse("Succeed!",true);
    }

    @GetMapping
    public ApiResponse getList(){
        return new ApiResponse("Succeed!",true,carRepository.findAll());
    }

    @GetMapping("getByRegionId/{id}")
    private List<Car> getByRegionId(@PathVariable Integer id){
        return carRepository.findAllByRegionId(id);
    }
    @PutMapping("/{id}")
    public  ApiResponse edit(@RequestBody Car carReq){
        Optional<Car> carOptional = carRepository.findById(carReq.getId());
        if (!carOptional.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
        if (!carReq.getModel().isEmpty()) carOptional.get().setModel(carReq.getModel());
        if (carReq.getPrice() != null) carOptional.get().setPrice(carReq.getPrice());
        if (carReq.getYear() != null) carOptional.get().setYear(carReq.getYear());
        carRepository.save(carOptional.get());
        return new ApiResponse("Edited!",true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (carRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        carRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }
}
