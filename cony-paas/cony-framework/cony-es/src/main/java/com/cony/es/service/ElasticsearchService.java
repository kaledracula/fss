package com.cony.es.service;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangk-p on 2017/11/9.
 */
@Service
public class ElasticsearchService {

    @Autowired
    private TransportClient client;

}
