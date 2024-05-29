package com.project.service;

import java.util.List;

import com.project.model.StateVo;

public interface StateService {

	void save(StateVo stateVo);

	List<StateVo> search();

	void delete(int id, StateVo v1);

	StateVo seachById(int id);
}
