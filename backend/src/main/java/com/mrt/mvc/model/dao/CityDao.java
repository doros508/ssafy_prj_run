package com.mrt.mvc.model.dao;

import com.mrt.mvc.model.dto.City;
import java.util.List;

public interface CityDao {
 List<City> selectAll();
 City selectByNo(int cityNo);
 int insert(City city);
 int update(City city);
 int delete(int cityNo);
}

