package com.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVo;
import com.project.model.DegreeVo;
import com.project.service.DegreeService;


@Controller
public class DegreeController {
	

	private DegreeService degreeService;

	public DegreeController(DegreeService degreeService) {
		this.degreeService = degreeService;
	}
	
	
	@GetMapping(value = "admin/degrees")
	public ModelAndView degrees() {
		List<DegreeVo> degreeList = this.degreeService.search();
		return new ModelAndView("admin/viewDegree","degreeList",degreeList);
	}

	@GetMapping(value = "admin/addDegree")
	public ModelAndView addDegree() {
		
		return new ModelAndView("admin/addDegree","DegreeVo",new DegreeVo());
	}
	
	@PostMapping(value="admin/saveDegree")
	public ModelAndView saveDegree(@ModelAttribute DegreeVo degVo){
		this.degreeService.save(degVo);
		return new ModelAndView("redirect:degrees");
	}
	
	@GetMapping(value="admin/deleteDegree")
	public ModelAndView deleteDegree(@RequestParam("id") int id){
		
		this.degreeService.delete(id);
		return new ModelAndView("redirect:degrees");
		
	}
	
	@GetMapping(value="admin/editDegree")
	public ModelAndView editDegree(@RequestParam("id") int id){
		
		DegreeVo degreeVo = this.degreeService.searchById(id);
		return new ModelAndView("admin/addDegree","DegreeVo",degreeVo);
		
	}
	
	
}
