package com.cony.es;
//
//import com.glodon.coca.EsSampleApplication;
//import com.glodon.coca.es.entity.Article;
//import com.glodon.coca.es.entity.ElasticSearchPage;
//import com.glodon.coca.es.repository.TransportClientRepository;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.rmi.server.ExportException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * Created by wangk-p on 2017/11/10.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = EsSampleApplication.class)
//public class SearchBaseTest {
//    @Autowired
//    TransportClientRepository client;
//
////    @Test
////    public void createIdx() throws Exception {
//////        client.createIndex("testmp2");
//////    }
////    @Test
////    public void saveMapping() throws Exception {
////        Map<String,Map<String,Object>> fields = new HashMap();
////        Map<String,Object> filedProperties = new HashMap();
////        filedProperties.put("type","text");
////        filedProperties.put("analyzer","ik_max_word");
////        filedProperties.put("search_analyzer","ik_max_word");
////        fields.put("title",filedProperties);
////        fields.put("description",filedProperties);
////        client.createMapping("testmp2","article",fields);
////    }
//
////    @Test
////    public void saveIdx()  throws Exception{
////        Article article = new Article();
////        client.createType("blog2","article",article);
////    }
////        @Test
////        public void saveDoc() throws Exception{
////            Article article = new Article();
////            article.setDescription("公安部：各地校车将享最高路权");
////            article.setTitle("公安部");
////            article.setId(UUID.randomUUID().toString());
////            article.setHits(0);
////            article.setBeginDateString("2017/6/7");
////            System.out.println(client.saveDoc("testmp2","article",article.getId(),article));
////        }
////
//        @Test
//        public void searchFullText(){
//            Article param = new Article();
//            param.setDescription("中国");
//            ElasticSearchPage<Article> page= new ElasticSearchPage<Article>();
//            page.setPageSize(10);
//            HighlightBuilder highlight = new HighlightBuilder();
//            highlight.field("description").preTags("<span style=\"color:red\">").postTags("</span>");
//
//            page = client.searchFullText(param,page,highlight,"testmp2");
//            for(Article aa : page.getRetList()){
//                System.out.println(aa.getId() +"===="+aa.getDescription()+"===title:=="+aa.getTitle());
//            }
//            System.out.println(page.getTotal());
//        }
//}
