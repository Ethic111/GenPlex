package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ReportTypeDao;
import com.project.model.ReportTypeVo;

@Service
@Transactional
public class ReportTypeServiceImp implements ReportTypeService{
	
	private ReportTypeDao reportTypeDao;

	public ReportTypeServiceImp(ReportTypeDao reportTypeDao) {
		this.reportTypeDao = reportTypeDao;
	}

	public List<ReportTypeVo> search() {
		List<ReportTypeVo> searchList = this.reportTypeDao.search();
		return searchList;

	}

	public void save(ReportTypeVo reportTypeVo) {
		reportTypeVo.setStatus(true);
		this.reportTypeDao.save(reportTypeVo);
	}

	public void delete(int id) {
		ReportTypeVo reportTypevo = new ReportTypeVo();
		reportTypevo.setId(id);
		reportTypevo.setStatus(false);
		this.reportTypeDao.save(reportTypevo);
	}

	public ReportTypeVo searchById(int id) {
		ReportTypeVo reportTypevo = this.reportTypeDao.searchById(id);
		return reportTypevo;
	}


}
