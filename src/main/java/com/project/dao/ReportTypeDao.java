package com.project.dao;

import java.util.List;

import com.project.model.ReportTypeVo;

public interface ReportTypeDao {

	List<ReportTypeVo> search();

	void save(ReportTypeVo reportTypeVo);

	ReportTypeVo searchById(int id);

}
