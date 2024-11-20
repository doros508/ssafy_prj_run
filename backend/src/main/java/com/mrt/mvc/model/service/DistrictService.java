package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.District;

public interface DistrictService {
	   List<District> getAllDistricts();
	   District getDistrictByNo(int districtNo);
	   List<District> getDistrictsByCity(int cityNo);
	   boolean createDistrict(District district);
	   boolean updateDistrict(District district);
	   boolean deleteDistrict(int districtNo);
}
