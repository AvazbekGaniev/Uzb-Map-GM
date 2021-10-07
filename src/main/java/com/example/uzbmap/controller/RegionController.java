package com.example.uzbmap.controller;

import com.example.uzbmap.entity.Region;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.payload.RegionDto;
import com.example.uzbmap.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;


    @PostMapping
    public ApiResponse save(@RequestBody RegionDto regionDto){
        Region region =new Region();
        region.setName(regionDto.getName());
        regionRepository.save(region);
        return new ApiResponse("Succeed!",true);
    }

    @GetMapping
    private List<Region> getAll(){
        return regionRepository.findAll();
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody RegionDto regionDto){

        Optional<Region> regionOptional = regionRepository.findById(id);
        if (!regionOptional.isPresent())return new ApiResponse("Data Is Incorrect!",false);
        if (!regionDto.getName().isEmpty()) regionOptional.get().setName(regionDto.getName());


        regionRepository.save(regionOptional.get());

        return new ApiResponse("Succeed!",true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (regionRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        regionRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }
}
