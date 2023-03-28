package com.example.elasticconsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

@Service
public class KafkaUserService {

//    @Autowired
//    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;







    public void savePlan(String plan) {
//        edao.save(plan);
        IndexQuery indexQuery = new IndexQuery();
//        indexQuery.setIndexName("my_index");
//        indexQuery.setType("my_type");
        indexQuery.setSource(plan);

        elasticsearchRestTemplate.index(indexQuery, elasticsearchRestTemplate.getIndexCoordinatesFor(String.class));
    }

//    public Iterable<JsonNode> findAllPlans() {
//        return edao.findAll();
//    }
}
