package com.mrt.mvc.model.service;

import java.util.List;

import com.mrt.mvc.model.dto.City;

public interface CityService {
	  List<City> getAllCities();
	    City getCityByNo(int cityNo);
	    boolean createCity(City city);
	    boolean updateCity(City city);
	    boolean deleteCity(int cityNo);
}
