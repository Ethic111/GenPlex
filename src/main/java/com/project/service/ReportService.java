package com.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.model.ReportVo;

public interface ReportService {
	
	void save(ReportVo reportVo,MultipartFile reportFile,HttpServletRequest request);
	
	List<ReportVo> search();

}
