package com.project.service;

import java.util.List;

import com.project.model.CityVo;

public interface CityService {
	
	void save(CityVo cityVo);

	List<CityVo> search();

	void delete(int id, CityVo cityVo);

	CityVo seachById(int id);

	List<CityVo> getCitiesByStateID(int stateId);
	
	CityVo getStateByCityID(int cityId);
}
