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
public class PersonDataService {

  @Value("${person.data.api.url}")
  private String personDataApiEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  public List<String> fetchPersonData(int jobCount) throws JsonProcessingException {

    long timeStarted = System.currentTimeMillis();
    HttpHeaders httpHeaders = new HttpHeaders();
    List<String> list = new ArrayList<>();

    HttpEntity<Object> requestEntity = new HttpEntity<>(null, httpHeaders);

    try {

      List<FutureTask> futureTasks = new ArrayList<>();

      for (int i = 0; i < jobCount; i++) {
        Callable<List<String>> newsCall = new Callable<List<String>>() {
          @Override
          public List<String> call() throws Exception {
            List<String> namesList = new ArrayList<>();
            ResponseEntity<String> names1 = restTemplate.exchange(personDataApiEndpoint, HttpMethod.GET,
                requestEntity, String.class);
            JSONArray jsonArray1 = new JSONArray(names1.getBody());
            jsonArray1.iterator().forEachRemaining((c) -> {
              if (Objects.nonNull(((JSONObject) c).get("name"))) {
                try {
                  namesList.add(((String) ((JSONObject) c).get("name")));
                } catch (Exception e) {
                  //e.printStackTrace();
                }
              }
            });
            return namesList;
          }
        };
        futureTasks.add(new FutureTask(newsCall));

      }

      for (FutureTask futureTask : futureTasks)
        new Thread(futureTask).start();

      for (FutureTask futureTask : futureTasks) {
        Thread.sleep(1000);
        list.addAll((Collection<? extends String>) futureTask.get());
      }

      System.out.println("Time taken :" + (System.currentTimeMillis() - timeStarted) / 1000.0 +  " for " + list.size() + " records");
      return list;

    }catch (Exception e){
      e.printStackTrace();
    }
    return  new ArrayList<>();
  }

  public List<String> fetchPersonDataSlow(int jobCount) throws JsonProcessingException {

    long timeStarted = System.currentTimeMillis();
    HttpHeaders httpHeaders = new HttpHeaders();
    List<String> list = new ArrayList<>();

    HttpEntity<Object> requestEntity = new HttpEntity<>(null, httpHeaders);

    try {

      for(int i = 0 ; i < jobCount ; i++) {
        List<String> namesList1 = new ArrayList<>();
        ResponseEntity<String> names1 = restTemplate.exchange(personDataApiEndpoint, HttpMethod.GET,
            requestEntity, String.class);

        JSONArray jsonArray1 = new JSONArray(names1.getBody());
        jsonArray1.iterator().forEachRemaining((c) -> {
          if (Objects.nonNull(((JSONObject) c).get("name"))) {
            try {
              namesList1.add(((String) ((JSONObject) c).get("name")));
            } catch (Exception e) {
              //e.printStackTrace();
            }
          }
        });
        list.addAll(namesList1);
      }




    }catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("Time taken :" + (System.currentTimeMillis() - timeStarted) / 1000.0 +  " for " + list.size() + " records");
    return list;

  }

}
