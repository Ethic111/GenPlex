package com.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.ReportTypeVo;
import com.project.service.ReportTypeService;

@Controller
public class ReportTypeController {
	
	private ReportTypeService reportTypeService;

	public ReportTypeController(ReportTypeService reportTypeService) {
		this.reportTypeService = reportTypeService;
	}
	
	
	@GetMapping(value = "admin/reportTypes")
	public ModelAndView reportTypes() {
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
		return new ModelAndView("admin/viewReportType","reportTypeList",reportTypeList);
	}

	@GetMapping(value = "admin/addReportType")
	public ModelAndView addReportType() {
		return new ModelAndView("admin/addReportType","ReportTypeVo",new ReportTypeVo());
	}
	
	@PostMapping(value="admin/saveReportType")
	public ModelAndView saveReportType(@ModelAttribute ReportTypeVo reportTypeVo){
		this.reportTypeService.save(reportTypeVo);
		return new ModelAndView("redirect:reportTypes");
	}
	
	@GetMapping(value="admin/deleteReportType")
	public ModelAndView deleteReportType(@RequestParam("id") int id){
		
		this.reportTypeService.delete(id);
		return new ModelAndView("redirect:reportTypes");
		
	}
	
	@GetMapping(value="admin/editReportType")
	public ModelAndView editReportType(@RequestParam("id") int id){
		
		ReportTypeVo reportTypeVo = this.reportTypeService.searchById(id);
		return new ModelAndView("admin/addReportType","ReportTypeVo",reportTypeVo);
		
	}
	
	

}
