package com.mrt.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.model.dto.District;
import com.mrt.mvc.model.service.DistrictService;

@RestController
@RequestMapping("/maratalk/district")
@CrossOrigin("*")
public class DistrictController {

   @Autowired
   private DistrictService districtService;

   @GetMapping
   public ResponseEntity<List<District>> getAllDistricts() {
       return new ResponseEntity<>(districtService.getAllDistricts(), HttpStatus.OK);
   }

   @GetMapping("/{districtNo}")
   public ResponseEntity<District> getDistrict(@PathVariable int districtNo) {
       District district = districtService.getDistrictByNo(districtNo);
       if (district != null) {
           return new ResponseEntity<>(district, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   
   @GetMapping("/city/{cityNo}")
   public ResponseEntity<List<District>> getDistrictsByCity(@PathVariable int cityNo) {
       return new ResponseEntity<>(districtService.getDistrictsByCity(cityNo), HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<String> createDistrict(@RequestBody District district) {
       if (districtService.createDistrict(district)) {
           return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }

   @PutMapping("/{districtNo}")
   public ResponseEntity<String> updateDistrict(@PathVariable int districtNo, @RequestBody District district) {
       district.setDistrictNo(districtNo);
       if (districtService.updateDistrict(district)) {
           return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }

   @DeleteMapping("/{districtNo}")
   public ResponseEntity<String> deleteDistrict(@PathVariable int districtNо) { 
      if (districtService.deleteDistrict(districtNо)) { 
         return new ResponseEntity<>("Successfully deleted", HttpStatus.OK); 
      } 
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
  } 
}