package com.mrt.mvc.model.dao;

import com.mrt.mvc.model.dto.District;
import java.util.List;

public interface DistrictDao {
	List<District> selectAll();

	District selectByNo(int districtNo);

	List<District> selectByCity(int cityNo);

	int insert(District district);

	int update(District district);

	int delete(int districtNo);
}