package com.blogproject.core.service.Impl;

import com.blogproject.core.service.SearchByTextService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = SearchByTextService.class, name = "Service to implement search by text using QueryBuilder", immediate = true)
public class SearchByTextServiceImpl implements SearchByTextService {

    private static final Logger LOG= LoggerFactory.getLogger(SearchByTextServiceImpl.class);


    @Reference
    private QueryBuilder queryBuilder;


//    @Activate
//    public void activate(){
//        LOG.info("----------Search By Text Service Activated--------------");
//    }

    public Map<String,String> createTextSearchQuery(String searchText){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("path","/content/blogproject");
        queryMap.put("fulltext",searchText);

        return queryMap;
    }

    @Override
    public JsonObject getResult(ResourceResolver resolver ,String searchText)  {
        Session session = resolver.adaptTo(Session.class);
        JsonObject searchResult = new JsonObject();
        Map<String,String> queryMap = createTextSearchQuery(searchText);

        Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap),session);

        SearchResult result = query.getResult();
        List<Hit> hits = result.getHits();
        JsonArray hitArray = new JsonArray();
        try{
            for(Hit hit : hits){
                JsonObject obj = new JsonObject();
                Resource resource = hit.getResource();
                //Page hitPage = resource.adaptTo(Page.class);

                obj.addProperty("title",resource.getValueMap().get("jcr:title",String.class));
                obj.addProperty("path",resource.getPath());
                hitArray.add(obj);
            }
            searchResult.addProperty("Result",hitArray.toString());
            return searchResult;
        } catch (Exception e) {
            searchResult.addProperty("Error",e.toString());
            return searchResult;
        }

    }
}
