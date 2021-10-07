package com.example.uzbmap.controller;


import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.District;
import com.example.uzbmap.payload.AddressDto;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.repository.AddressRepository;
import com.example.uzbmap.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictRepository districtRepository;

    @PostMapping
    public ApiResponse save(@RequestBody AddressDto addressDto){
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        if (!optionalDistrict.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
        address.setDistrict(optionalDistrict.get());
        addressRepository.save(address);
        return new ApiResponse("Succeed!",true);
    }

    @GetMapping
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    @GetMapping("/byDistId/{id}")
    public ApiResponse getByDistId(@PathVariable Integer id){
        if (addressRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        return new ApiResponse("Succeed!",true, addressRepository.byDistId(id));
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!addressDto.getStreet().isEmpty()) optionalAddress.get().setStreet(addressDto.getStreet());
        if (addressDto.getHomeNumber() != null)  optionalAddress.get().setHomeNumber(addressDto.getHomeNumber());
        if (addressDto.getDistrictId() != null){
            Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
            if (optionalDistrict.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
            optionalAddress.get().setDistrict(optionalDistrict.get());
        }
        return new ApiResponse("Succeed!",true);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (addressRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        addressRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }
}
