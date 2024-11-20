package com.mrt.mvc.controller;


import java.util.List;

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

import com.mrt.mvc.model.dto.Race;
import com.mrt.mvc.model.service.RaceService;

@RestController
@RequestMapping("/api/race")
@CrossOrigin("*")
public class RaceController {

    private RaceService raceService;

    public RaceController(RaceService raceService) {
		this.raceService = raceService;
	}

	@GetMapping
    public ResponseEntity<List<Race>> getAllRaces() {
        return new ResponseEntity<>(raceService.getAllRaces(), HttpStatus.OK);
    }

    @GetMapping("/{raceNo}")
    public ResponseEntity<Race> getRace(@PathVariable int raceNo) {
        Race race = raceService.getRaceByNo(raceNo);
        if (race != null) {
            return new ResponseEntity<>(race, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createRace(@RequestBody Race race) {
        if (raceService.createRace(race)) {
            return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{raceNo}")
    public ResponseEntity<String> updateRace(@PathVariable int raceNo, @RequestBody Race race) {
        race.setRaceNo(raceNo);
        if (raceService.updateRace(race)) {
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{raceNo}")
    public ResponseEntity<String> deleteRace(@PathVariable int raceNo) {
        if (raceService.deleteRace(raceNo)) {
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/city/{cityNo}")
    public ResponseEntity<List<Race>> getRacesByCity(@PathVariable int cityNo) {
        return new ResponseEntity<>(raceService.getRacesByCity(cityNo), HttpStatus.OK);
    }
}