package com.example.uzbmap.controller;

import com.example.uzbmap.entity.*;
import com.example.uzbmap.payload.AddressDto;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.payload.AutoShopDto;
import com.example.uzbmap.payload.DistrictDto;
import com.example.uzbmap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autoShop")
public class AutoShopController {
    @Autowired
    AutoShopRepository autoShopRepository;
    @Autowired
    GmRepository gmRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public ApiResponse save(@RequestBody AutoShopDto autoShopDto) {
        AutoShop autoShop = new AutoShop();
        autoShop.setName(autoShopDto.getName());

        Optional<Address> optionalAddress = addressRepository.findById(autoShopDto.getAddressId());
        if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
        autoShop.setAddress(optionalAddress.get());

        Optional<Gm> optionalGm = gmRepository.findById(autoShopDto.getAddressId());
        if (!optionalGm.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
        autoShop.setGm(optionalGm.get());

        List<Car> carList = carRepository.findAllById(autoShopDto.getCarIds());
        autoShop.setCars(carList);

        return new ApiResponse("Succeed!", true);
    }


    @GetMapping
    public List<AutoShop> getAll() {
        return autoShopRepository.findAll();
    }
    @GetMapping("/listByGmId/{id}")
    public List<AutoShop> getByGmId(@PathVariable Integer id){
        return autoShopRepository.findAllByGmId(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody AutoShopDto autoShopDto) {

        Optional<AutoShop> optionalAutoShop = autoShopRepository.findById(id);
        if (!optionalAutoShop.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
        AutoShop autoShop = optionalAutoShop.get();

        if (!autoShopDto.getName().isEmpty()) autoShop.setName(autoShopDto.getName());

        if (autoShopDto.getAddressId() != null) {
            Optional<Address> optionalAddress = addressRepository.findById(autoShopDto.getAddressId());
            if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
            autoShop.setAddress(optionalAddress.get());
        }
        if (autoShopDto.getGmId() != null) {
            Optional<Gm> optionalGm = gmRepository.findById(autoShopDto.getAddressId());
            if (!optionalGm.isPresent()) return new ApiResponse("Data Is Incorrect!", false);
            autoShop.setGm(optionalGm.get());
        }
        if (autoShopDto.getCarIds() != null) {
            List<Car> carList = carRepository.findAllById(autoShopDto.getCarIds());
            autoShop.setCars(carList);
        }
        autoShopRepository.save(autoShop);
        return new ApiResponse("Succeed!", true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (autoShopRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        autoShopRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }
}
