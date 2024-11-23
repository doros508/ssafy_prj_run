package com.mrt.mvc.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.SearchCondition;
import com.mrt.mvc.model.service.CrewService;

@RestController
@RequestMapping("/maratalk")
@CrossOrigin("*")
public class CrewController {
    
    private final CrewService service;
    
    public CrewController(CrewService service) {
        this.service = service;
    }
    
    // 특정 위치를 기준으로 검색
    @GetMapping("/crews/location")
    public ResponseEntity<List<Crew>> getCrewsByLocation(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5") double radius) {
        List<Crew> crews = service.getCrewsByLocation(lat, lng, radius);
        return crews != null && !crews.isEmpty()
            ? new ResponseEntity<>(crews, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // 검색 조건에 맞는 크루 리스트 반환
    @GetMapping("/crews")
    public ResponseEntity<List<Crew>> getCrews(@ModelAttribute SearchCondition condition) {
        List<Crew> crews = service.getCrewList(condition);
        return crews != null && !crews.isEmpty()
            ? new ResponseEntity<>(crews, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // 특정 크루 상세 정보 반환
    @GetMapping("/crew/{no}")
    public ResponseEntity<Crew> detail(@PathVariable("no") int no) {
        Crew crew = service.getCrewByNo(no);
        return crew != null
            ? new ResponseEntity<>(crew, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // 크루 등록
    @PostMapping("/crew")
    public ResponseEntity<String> write(@RequestBody Crew crew) {
        return service.writeCrew(crew)
            ? new ResponseEntity<>(crew.getCrewNo() + "번 크루가 등록되었습니다.", HttpStatus.CREATED)
            : new ResponseEntity<>("크루 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 크루 정보 수정
    @PutMapping("/crew/{no}")
    public ResponseEntity<String> updateCrew(@PathVariable("no") int no, @RequestBody Crew crew) {
        crew.setCrewNo(no);
        return service.modify(crew)
            ? new ResponseEntity<>("크루 정보가 수정되었습니다.", HttpStatus.OK)
            : new ResponseEntity<>("수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 크루 삭제
    @DeleteMapping("/crew/{no}")
    public ResponseEntity<String> delete(@PathVariable("no") int no) {
        return service.removeCrew(no)
            ? ResponseEntity.ok(no + "번 크루가 삭제되었습니다.")
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제에 실패했습니다.");
    }
}
