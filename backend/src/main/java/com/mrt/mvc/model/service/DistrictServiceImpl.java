package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.DistrictDao;
import com.mrt.mvc.model.dto.District;
@Service
public class DistrictServiceImpl implements DistrictService {

	private DistrictDao districtDao;
	
	
	public DistrictServiceImpl(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	  @Override
	   public List<District> getAllDistricts() {
	       return districtDao.selectAll();
	   }

	   @Override
	   public District getDistrictByNo(int districtNo) {
	       return districtDao.selectByNo(districtNo);
	   }

	   @Override
	   public List<District> getDistrictsByCity(int cityNo) {
	       return districtDao.selectByCity(cityNo);
	   }

	   @Override
	   public boolean createDistrict(District district) {
	       return districtDao.insert(district) > 0;
	   }

	   @Override
	   public boolean updateDistrict(District district) {
	       return districtDao.update(district) > 0;
	   }

	   @Override
	   public boolean deleteDistrict(int districtNo) {
	       return districtDao.delete(districtNo) > 0;
	   }
	}