package com.controller.rest;
/*
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/news")

 */
public class KhelNewsController {
/*
	@Value("${khel.newsapi.url}")
	private String newsApiEndpoint;

	@Autowired
	private RestTemplate restTemplate;

	@CrossOrigin(origins = "${application.site.url}" )
	@GetMapping(path = "",produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches sports news of India")
	public ResponseEntity<String> getSportsNews(){

		HttpHeaders httpHeaders = new HttpHeaders();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGetRequest = new HttpGet("");
		
		HttpResponse response = client.execute(httpGetRequest);
	    org.apache.http.HttpEntity     response.getEntity();
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(null, httpHeaders);
		return restTemplate.exchange(newsApiEndpoint, HttpMethod.GET, requestEntity, String.class) ; 

		
	}
*/
}
