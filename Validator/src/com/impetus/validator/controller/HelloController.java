package com.impetus.validator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String connectDataSources(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public String startMetaDataValidation(ModelMap model) {
		return "metadata";
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String startDataValidation(ModelMap model) {
		return "data";
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String downloadReport(ModelMap model) {
		model.addAttribute("msg", "Download Report");
		return "report";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String printError(ModelMap model) {
		model.addAttribute("msg", "Error occured!");
		return "error";
	}
}
