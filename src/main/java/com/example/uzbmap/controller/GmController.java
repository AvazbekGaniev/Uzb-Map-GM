package com.example.uzbmap.controller;

import com.example.uzbmap.entity.Address;
import com.example.uzbmap.entity.District;
import com.example.uzbmap.entity.Gm;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.payload.GmDto;
import com.example.uzbmap.repository.AddressRepository;
import com.example.uzbmap.repository.GmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gm")
public class GmController {
    @Autowired
    GmRepository gmRepository;
    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public ApiResponse save(@RequestBody GmDto gmDto){
        Gm gm = new Gm();
        gm.setCorpName(gmDto.getCorpName());
        gm.setDirectorName(gmDto.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(gmDto.getAddressId());
        if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
        gm.setAddress(optionalAddress.get());
        gmRepository.save(gm);
        return new ApiResponse("Succeed!",true);
    }
    @GetMapping
    public List<Gm> getAll(){
        return gmRepository.findAll();
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody GmDto gmDto){

        Optional<Gm> gmOptional = gmRepository.findById(id);
        if (!gmOptional.isPresent())  return new ApiResponse("Data Is Incorrect!",false);
        Gm gm = gmOptional.get();
        if (!gmDto.getCorpName().isEmpty())gm.setCorpName(gmDto.getCorpName());
        if (!gmDto.getDirectorName().isEmpty())  gm.setDirectorName(gmDto.getDirectorName());
        if (gmDto.getAddressId() != null){
            Optional<Address> optionalAddress = addressRepository.findById(gmDto.getAddressId());
            if (!optionalAddress.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
            gm.setAddress(optionalAddress.get());
        }
        gmRepository.save(gm);
        return new ApiResponse("Succeed!",true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (gmRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        gmRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }

}
