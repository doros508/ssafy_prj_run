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
public class CrewController {
    
    private final CrewService service;
    
    public CrewController(CrewService service) {
        this.service = service;
    }
    
    @GetMapping("/crew")
    public ResponseEntity<List<Crew>> list(@ModelAttribute SearchCondition condition) {
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
}