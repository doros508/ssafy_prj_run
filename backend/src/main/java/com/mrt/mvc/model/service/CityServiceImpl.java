package com.mrt.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.CityDao;
import com.mrt.mvc.model.dto.City;
@Service
public class CityServiceImpl implements CityService {

	private CityDao cityDao;
	
	public CityServiceImpl(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	  @Override
	    public List<City> getAllCities() {
	        return cityDao.selectAll();
	    }

	    @Override
	    public City getCityByNo(int cityNo) {
	        return cityDao.selectByNo(cityNo);
	    }

	    @Override
	    public boolean createCity(City city) {
	        return cityDao.insert(city) > 0;
	    }

	    @Override
	    public boolean updateCity(City city) {
	        return cityDao.update(city) > 0;
	    }

	    @Override
	    public boolean deleteCity(int cityNo) {
	        return cityDao.delete(cityNo) > 0;
	    }
	}