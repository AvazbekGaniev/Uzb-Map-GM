package com.example.uzbmap.controller;


import com.example.uzbmap.entity.District;
import com.example.uzbmap.entity.Region;
import com.example.uzbmap.payload.ApiResponse;
import com.example.uzbmap.payload.DistrictDto;
import com.example.uzbmap.repository.DistrictRepository;
import com.example.uzbmap.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RegionRepository regionRepository;

    @PostMapping
    public ApiResponse save(@RequestBody DistrictDto districtDto){
        District district = new District();
        district.setName(districtDto.getName());
        Optional<Region> regionOptional = regionRepository.findById(districtDto.getRegionId());
        if (!regionOptional.isPresent()) return  new ApiResponse("Data Is Incorrect!",false);
        district.setRegion(regionOptional.get());
        districtRepository.save(district);
        return new ApiResponse("Succeed!",true);
    }

    @GetMapping
    public List<District> getAll(){
        return districtRepository.findAll();
    }

    @GetMapping("byRegionId/{id}")
    public ApiResponse getByRegId(@PathVariable Integer id){
        if (districtRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        return new ApiResponse("Succeed!",true, districtRepository.byRegId(id));
    }



    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody DistrictDto districtDto){

        Optional<District> districtOptional = districtRepository.findById(id);
        if (!districtOptional.isPresent())return new ApiResponse("Data Is Incorrect!",false);
        if (!districtDto.getName().isEmpty()) districtOptional.get().setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (!optionalRegion.isPresent()) return new ApiResponse("Data Is Incorrect!",false);
        districtOptional.get().setRegion(optionalRegion.get());
        districtRepository.save(districtOptional.get());
        return new ApiResponse("Succeed!",true);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        if (districtRepository.existsById(id))  return new ApiResponse("Data Is Incorrect!",false);
        districtRepository.deleteById(id);
        return new ApiResponse("Succeed!",true);
    }
}
