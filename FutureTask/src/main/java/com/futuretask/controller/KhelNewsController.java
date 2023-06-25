package com.futuretask.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.futuretask.service.KhelNewsService;
import com.futuretask.service.PersonDataService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/news")
public class KhelNewsController {


	@Autowired
	private KhelNewsService khelNewsService ;

	@Autowired
	private PersonDataService personDataService;


	@GetMapping(path = "",produces= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches sports news of India")
	public List<String> getSportsNews(){

		try {
			return khelNewsService.fetchNews();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}
	@GetMapping(path = "/slow",produces= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches sports news of India")
	public List<String> getSportsNewsSlow(){

		try {
			return khelNewsService.fetchNewsSlow();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}


	@GetMapping(path = "/person/{jobCount}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches sports news of India")
	public List<String> getPersonData(@PathVariable  int jobCount){

		try {
			return personDataService.fetchPersonData(jobCount);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}
	@GetMapping(path = "/person/slow/{jobCount}",produces= MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches sports news of India")
	public List<String> getPersonDataSlow(@PathVariable int jobCount){

		try {
			return personDataService.fetchPersonDataSlow(jobCount);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}



}
