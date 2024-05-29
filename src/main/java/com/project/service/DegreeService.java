package com.project.service;

import java.util.List;

import com.project.model.DegreeVo;

public interface DegreeService {

	List<DegreeVo> search();
	
	void save(DegreeVo degVo);
	
	void delete(int id);
	
	DegreeVo searchById(int id);
}
