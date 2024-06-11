package com.project.dao;

import java.util.List;

import com.project.model.ReportVo;

public interface ReportDao {
	
	void save(ReportVo reportVo);
	
	List<ReportVo> search();

}
