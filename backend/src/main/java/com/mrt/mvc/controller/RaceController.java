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
@RequestMapping("/maratalk")
//@CrossOrigin("*")
public class RaceController {

	// 의존성 주입
    private RaceService service;
    public RaceController(RaceService service) {
		this.service = service;
	}

	@GetMapping("/race")
    public ResponseEntity<List<Race>> list() {
        return new ResponseEntity<>(service.getRaceList(), HttpStatus.OK);
    }

    @GetMapping("/race/{no}")
    public ResponseEntity<Race> detail(@PathVariable("no") int no) {
        Race race = service.getRaceByNo(no);
        if (race != null) {
            return new ResponseEntity<>(race, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/race")
    public ResponseEntity<String> write(@RequestBody Race race) {
        if (service.createRace(race)) {
            return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/race/{no}")
    public ResponseEntity<String> updateRace(@PathVariable int no, @RequestBody Race race) {
        race.setRaceNo(no);
        if (service.modifyRace(race)) {
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{raceNo}")
    public ResponseEntity<String> deleteRace(@PathVariable int raceNo) {
        if (service.removeRace(raceNo)) {
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/city/{cityNo}")
    public ResponseEntity<List<Race>> getRacesByCity(@PathVariable int cityNo) {
        return new ResponseEntity<>(service.getRacesByCity(cityNo), HttpStatus.OK);
    }
}