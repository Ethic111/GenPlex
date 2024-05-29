package com.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.StateVo;
import com.project.service.StateService;

@Controller
public class StateController {

	private StateService stateService;

	public StateController(StateService stateService) {
		this.stateService = stateService;
	}

	@GetMapping(value = "addState")
	public ModelAndView addState() {
		return new ModelAndView("admin/addState", "StateVo", new StateVo());
	}

	@PostMapping(value = "insertState")
	public ModelAndView insertState(@ModelAttribute StateVo stateVo) {
		this.stateService.save(stateVo);
		return new ModelAndView("redirect:states");
	}

	@GetMapping(value = "states")
	public ModelAndView viewStates() {
		List<StateVo> stateList = this.stateService.search();
		return new ModelAndView("admin/viewStates", "stateList", stateList);
	}

	@GetMapping(value = "deleteState")
	public ModelAndView deleteState(@RequestParam("id") int id) {

		StateVo stateVO = this.stateService.seachById(id);
		this.stateService.delete(id, stateVO);
		return new ModelAndView("redirect:states");
	}

	@GetMapping(value = "editState")
	public ModelAndView editState(@RequestParam("id") int id) {
		StateVo stateVo = this.stateService.seachById(id);
		return new ModelAndView("admin/addState", "StateVo", stateVo);
	}
}
