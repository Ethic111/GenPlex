package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.StateDao;
import com.project.model.StateVo;

@Service
@Transactional
public class StateServiceImp implements StateService {

	private StateDao stateDao;

	public StateServiceImp(StateDao stateDao) {
		this.stateDao = stateDao;
	}

	public void save(StateVo stateVo) {

		stateVo.setStatus(true);
		this.stateDao.save(stateVo);
	}

	public List<StateVo> search() {
		List<StateVo> stateList = this.stateDao.search();
		return stateList;
	}

	public void delete(int id, StateVo v1) {

		v1.setId(id);
		v1.setStatus(false);
		System.out.println(v1.getStateName());
		this.stateDao.save(v1);

	}

	public StateVo seachById(int id) {
		StateVo regvo = this.stateDao.seachById(id);
		return regvo;
	}
}
