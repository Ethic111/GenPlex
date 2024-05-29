package com.project.dao;

import java.util.List;

import com.project.model.DegreeVo;

public interface DegreeDao {
	
	List<DegreeVo> search();
	
	void save(DegreeVo degVo);
	
	DegreeVo searchById(int id);

}
