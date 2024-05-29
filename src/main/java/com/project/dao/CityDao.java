package com.project.dao;

import java.util.List;

import com.project.model.CityVo;

public interface CityDao {

	void save(CityVo cityVo);

	List<CityVo> search();

	CityVo seachById(int id);
	
	List<CityVo> searchCitiesByStateID(int stateId);
	
	CityVo seachByCityId(int cityId);

}
