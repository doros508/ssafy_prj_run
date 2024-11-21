package com.mrt.mvc.model.service;

import java.util.List;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.SearchCondition;

public interface CrewService {
    List<Crew> getCrewList(SearchCondition condition);
    Crew getCrewByNo(int crewNo);
    boolean writeCrew(Crew crew);
    boolean modify(Crew crew);
    boolean removeCrew(int crewNo);
    List<Crew> getCrewsByLocation(double lat, double lng, double radius);
}