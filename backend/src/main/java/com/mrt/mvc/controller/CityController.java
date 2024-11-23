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

import com.mrt.mvc.model.dto.City;
import com.mrt.mvc.model.service.CityService;

@RestController
@RequestMapping("/maratalk/city-info")  // 경로를 변경하여 raceController와 겹치지 않도록 수정
@CrossOrigin("*")
public class CityController {

   @Autowired
   private CityService cityService;

   @GetMapping
   public ResponseEntity<List<City>> getAllCities() {
       return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
   }

   @GetMapping("/{cityNo}")
   public ResponseEntity<City> getCity(@PathVariable int cityNo) {
       City city = cityService.getCityByNo(cityNo);
       if (city != null) {
           return new ResponseEntity<>(city, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping
   public ResponseEntity<String> createCity(@RequestBody City city) {
       if (cityService.createCity(city)) {
           return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }

   @PutMapping("/{cityNo}")
   public ResponseEntity<String> updateCity(@PathVariable int cityNo, @RequestBody City city) {
       city.setCityNo(cityNo);
       if (cityService.updateCity(city)) {
           return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }

   @DeleteMapping("/{cityNo}")
   public ResponseEntity<String> deleteCity(@PathVariable int cityNo) {
       if (cityService.deleteCity(cityNo)) {
           return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
