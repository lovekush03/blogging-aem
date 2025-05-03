package com.blogproject.core.service.Impl;

import com.blogproject.core.config.JsonApiUrlConfig;
import com.blogproject.core.models.Todo;
import com.blogproject.core.service.JsonApiService;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;
@Component(
        service = JsonApiService.class,
        immediate = true,
        name = "JSON API Service"
)
@Designate(ocd = JsonApiUrlConfig.class)
public class JsonApiServiceImpl implements JsonApiService {

    private String apiUrl;
    private List<Todo> todoList;

    @Activate
    @Modified
    public void activate(JsonApiUrlConfig jsonApiUrlConfig){
        this.apiUrl = jsonApiUrlConfig.getURL();
    }

    @Override
    public JsonObject getTodos() {
        todoList = new ArrayList<>();
        String jsonData = "";
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet(apiUrl);
            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                if(entity != null){
                    jsonData = EntityUtils.toString(entity);
                }
            }
        }catch (Exception e){
            return null;
        }
        JsonObject json = new JsonObject();
        json.addProperty("result",jsonData);
        return json;
    }
}
