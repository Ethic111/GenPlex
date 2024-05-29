package com.project.dao;

import java.util.List;

import com.project.model.StateVo;

public interface StateDao {
	
	void save(StateVo stateVo);
	
	List<StateVo> search();

	StateVo seachById(int id);
}
