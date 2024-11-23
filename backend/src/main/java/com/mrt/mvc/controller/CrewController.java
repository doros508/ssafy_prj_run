package com.mrt.mvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrt.mvc.model.dto.City;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.CrewSearchCondition;
import com.mrt.mvc.model.dto.District;
import com.mrt.mvc.model.service.CrewService;

@RestController
@RequestMapping("/maratalk")
@CrossOrigin("**")
public class CrewController {
    
    private final CrewService service;
    
    public CrewController(CrewService service) {
        this.service = service;
    }
    @GetMapping("/crews/location")
    public ResponseEntity<List<Crew>> getCrewsByLocation(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5") double radius) {
        List<Crew> crews = service.getCrewsByLocation(lat, lng, radius);
        return ResponseEntity.ok(crews);
    }
    
    @GetMapping("/crew")
    public ResponseEntity<List<Crew>> list(@ModelAttribute CrewSearchCondition condition) {
        List<Crew> list = service.getCrewList(condition);
        return list != null && !list.isEmpty()
            ? new ResponseEntity<>(list, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/crew/{no}")
    public ResponseEntity<Crew> detail(@PathVariable("no") int no) {
        Crew crew = service.getCrewByNo(no);
        return crew != null 
            ? new ResponseEntity<>(crew, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/crew")
    public ResponseEntity<String> write(@RequestBody Crew crew) {
        return service.writeCrew(crew)
            ? new ResponseEntity<>(crew.getCrewNo() + "번 크루가 등록되었습니다.", HttpStatus.CREATED)
            : new ResponseEntity<>("크루 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PutMapping("/crew/{no}")
    public ResponseEntity<String> updateCrew(@PathVariable("no") int no, @RequestBody Crew crew) {
        crew.setCrewNo(no);
        return service.modify(crew)
            ? new ResponseEntity<>("크루 정보가 수정되었습니다.", HttpStatus.OK)
            : new ResponseEntity<>("수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping("/crew/{no}")
    public ResponseEntity<String> delete(@PathVariable("no") int no) {
        return service.removeCrew(no)
            ? ResponseEntity.ok(no + "번 크루가 삭제되었습니다.")
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제에 실패했습니다.");
    }
 // 시 목록 가져오기
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCityList() {
        List<City> cities = service.getCityList();
        return cities != null && !cities.isEmpty()
            ? new ResponseEntity<>(cities, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/cities/{cityNo}/districts")
    public ResponseEntity<List<District>> getDistrictList(@PathVariable("cityNo") int cityNo) {
        List<District> districts = service.getDistrictList(cityNo);
        return districts != null && !districts.isEmpty()
            ? new ResponseEntity<>(districts, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}