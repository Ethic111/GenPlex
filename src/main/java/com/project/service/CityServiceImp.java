package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.CityDao;
import com.project.model.CityVo;

@Service
@Transactional
public class CityServiceImp implements CityService {

	private CityDao cityDao;

	public CityServiceImp(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public void save(CityVo cityVo) {

		cityVo.setStatus(true);
		this.cityDao.save(cityVo);
	}

	public List<CityVo> search() {
		List<CityVo> cityList = this.cityDao.search();
		return cityList;
	}

	public void delete(int id, CityVo cityVo) {

		cityVo.setId(id);
		cityVo.setStatus(false);
		System.out.println(cityVo.getCityName());
		this.cityDao.save(cityVo);

	}

	public CityVo seachById(int id) {
		CityVo cityvo = this.cityDao.seachById(id);
		return cityvo;
	}

	public List<CityVo> getCitiesByStateID(int stateId) {
		List<CityVo> cityList = this.cityDao.searchCitiesByStateID(stateId);
		return cityList;
	}
	
	public CityVo getStateByCityID(int cityId){
		CityVo cityvo = this.cityDao.seachByCityId(cityId);
		return cityvo;
	}
}
