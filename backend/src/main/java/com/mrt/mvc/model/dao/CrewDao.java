package com.mrt.mvc.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.mrt.mvc.model.dto.Crew;
import com.mrt.mvc.model.dto.SearchCondition;

@Mapper
public interface CrewDao {
    List<Crew> selectAll(SearchCondition condition);
    Crew selectOne(int crewNo);
    int insert(Crew crew);
    int update(Crew crew);
    int delete(int crewNo);
    List<Crew> selectByLocation(double lat, double lng, double radius);
    
}