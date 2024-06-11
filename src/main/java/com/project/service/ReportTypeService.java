package com.project.service;

import java.util.List;

import com.project.model.ReportTypeVo;

public interface ReportTypeService {

	List<ReportTypeVo> search();

	void save(ReportTypeVo reportTypeVo);

	void delete(int id);

	ReportTypeVo searchById(int id);
}
