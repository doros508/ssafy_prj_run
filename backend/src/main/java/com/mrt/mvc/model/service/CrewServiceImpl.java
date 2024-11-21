package com.mrt.mvc.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mrt.mvc.model.dao.CrewDao;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.SearchCondition;

@Service
public class CrewServiceImpl implements CrewService {
    private final CrewDao dao;
    
    public CrewServiceImpl(CrewDao dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Crew> getCrewList(SearchCondition condition) {
        return dao.selectAll(condition);
    }
    
    @Override
    public Crew getCrewByNo(int crewNo) {
        return dao.selectOne(crewNo);
    }
    
    @Override
    public boolean writeCrew(Crew crew) {
        return dao.insert(crew) > 0;
    }
    
    @Override
    public boolean modify(Crew crew) {
        return dao.update(crew) > 0;
    }
    
    @Override
    public boolean removeCrew(int crewNo) {
        return dao.delete(crewNo) > 0;
    }
    
    @Override
    public List<Crew> getCrewsByLocation(double lat, double lng, double radius) {
        return dao.selectByLocation(lat, lng, radius);
    }
}