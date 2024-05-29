package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.DegreeDao;
import com.project.model.DegreeVo;

@Service
@Transactional
public class DegreeServiceImp implements DegreeService {

	private DegreeDao degreeDao;

	public DegreeServiceImp(DegreeDao degreeDao) {
		this.degreeDao = degreeDao;
	}

	public List<DegreeVo> search() {
		List<DegreeVo> searchList = this.degreeDao.search();
		return searchList;

	}

	public void save(DegreeVo degVo) {
		degVo.setStatus(true);
		this.degreeDao.save(degVo);
	}

	public void delete(int id) {
		DegreeVo degvo = new DegreeVo();
		degvo.setId(id);
		degvo.setStatus(false);
		this.degreeDao.save(degvo);
	}

	public DegreeVo searchById(int id) {
		DegreeVo degreevo = this.degreeDao.searchById(id);
		return degreevo;
	}

}
