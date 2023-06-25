package com.futuretask.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KhelNewsService {

  @Value("${khel.newsapi.url}")
  private String newsApiEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  private static final int JOB_COUNT = 500;


  public List<String> fetchNews() throws JsonProcessingException {

    long timeStarted = System.currentTimeMillis();
    HttpHeaders httpHeaders = new HttpHeaders();
    List<String> list = new ArrayList<>();

    HttpEntity<Object> requestEntity = new HttpEntity<>(null, httpHeaders);

    try {

      List<FutureTask> futureTasks = new ArrayList<>();

      for (int i = 0; i < JOB_COUNT; i++) {
        Callable<List<String>> newsCall = new Callable<List<String>>() {
          @Override
          public List<String> call() throws Exception {
            List<String> newsList = new ArrayList<>();
            ResponseEntity<String> news1 = restTemplate.exchange(newsApiEndpoint, HttpMethod.GET,
                requestEntity, String.class);
            JSONObject jsonObject1 = new JSONObject(news1.getBody());
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("articles");
            jsonArray1.iterator().forEachRemaining((c) -> {
              if (Objects.nonNull(((JSONObject) c).get("description"))) {
                try {
                  newsList.add(((String) ((JSONObject) c).get("description")));
                } catch (Exception e) {
                  //e.printStackTrace();
                }
              }
            });
            return newsList;
          }
        };
        futureTasks.add(new FutureTask(newsCall));

      }

      for (FutureTask futureTask : futureTasks)
        new Thread(futureTask).start();

      for (FutureTask futureTask : futureTasks) {
        list.addAll((Collection<? extends String>) futureTask.get());
      }

      System.out.println("Time taken :" + (System.currentTimeMillis() - timeStarted) / 1000.0 +  " for " + list.size() + " records");
      return list;

    }catch (Exception e){
      e.printStackTrace();
    }
    return  new ArrayList<>();
  }

  public List<String> fetchNewsSlow() throws JsonProcessingException {

    long timeStarted = System.currentTimeMillis();
    HttpHeaders httpHeaders = new HttpHeaders();
    List<String> list = new ArrayList<>();

    HttpEntity<Object> requestEntity = new HttpEntity<>(null, httpHeaders);

    try {

      for(int i = 0 ; i < JOB_COUNT ; i++) {
        List<String> newsList1 = new ArrayList<>();
        ResponseEntity<String> news1 = restTemplate.exchange(newsApiEndpoint, HttpMethod.GET,
            requestEntity, String.class);
        JSONObject jsonObject1 = new JSONObject(news1.getBody());
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("articles");
        jsonArray1.iterator().forEachRemaining((c) -> {
          if (Objects.nonNull(((JSONObject) c).get("description"))) {
            try {
              newsList1.add(((String) ((JSONObject) c).get("description")));
            } catch (Exception e) {
              //e.printStackTrace();
            }
          }
        });
        list.addAll(newsList1);
      }




    }catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("Time taken :" + (System.currentTimeMillis() - timeStarted) / 1000.0 +  " for " + list.size() + " records");
    return list;

  }

}
