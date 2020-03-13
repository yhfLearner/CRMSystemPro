package com.hwua.service.impl;

import com.hwua.config.LuceneConfig;
import com.hwua.pojo.Product;
import com.hwua.service.LuceneService;
import com.hwua.service.ProductMessageService;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class LuceneServiceImpl implements LuceneService {
    @Autowired
    private ProductMessageService promService;
    @Autowired
    private IndexWriter indexWriter;
    @Override
    public void createIndexDb() throws Exception {
        List<Product> products = promService.queryAllProducts();
        for (Product product : products) {
            Document document = new Document();
            Field idField = new StringField("id",product.getId()+"",Field.Store.YES);
            Field productNameField = new TextField("productName",product.getProductName(),Field.Store.YES);
            Field cityNameField = new TextField("cityName",product.getCityName(),Field.Store.YES);
            Field productDescField = new TextField("productDesc",product.getProductDesc()+"",Field.Store.YES);
            Field productNumField = new StringField("productNum",product.getProductNum()+"",Field.Store.YES);
            Field departureTimeDescField = new StringField("departureTime",product.getDepartureTime()+"",Field.Store.YES);
            Field productPriceField = new StringField("productPrice",product.getProductPrice()+"",Field.Store.YES);
            Field productStatusDescField = new StringField("productStatus",product.getProductStatus()+"",Field.Store.YES);
            document.add(idField);
            document.add(productNameField);
            document.add(cityNameField);
            document.add(productDescField);
            document.add(productNumField);
            document.add(departureTimeDescField);
            document.add(productPriceField);
            document.add(productStatusDescField);
            //5.把文档放入索引库中
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }

    @Override
    public List<Product> queryAllProduct(String term, Integer count) throws Exception {
        Directory directory = FSDirectory.open(new File(LuceneConfig.INDEX_PATH).toPath());

        List<Product> list = new ArrayList<>();
        IndexReader indexReader = DirectoryReader.open(directory);
        //3.创建IndexSearch对象,其内部提供了对索引库进行查询的API
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //4.创建Query查询对象,指定要查询的域和关键字
        String[] fields = {"productName","productDesc","cityName"};
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields,new IKAnalyzer());
        Query query = queryParser.parse(term);
        TopDocs topDocs = indexSearcher.search(query, count);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docId = scoreDoc.doc;//获取域中文档的id
            Document document = indexSearcher.doc(docId);
            String id = document.get("id");
            String productName = document.get("productName");
            String cityName = document.get("cityName");
            String productDesc = document.get("productDesc");
            String departureTime = document.get("departureTime");
            Timestamp timestamp = Timestamp.valueOf(departureTime);
            String productNum = document.get("productNum");
            String productStatus = document.get("productStatus");
            String productPrice = document.get("productPrice");
            Product product = new Product();
            product.setId(Integer.parseInt(id));
            product.setProductName(productName);
            product.setCityName(cityName);
            product.setProductDesc(productDesc);
            product.setDepartureTime(timestamp);
            product.setProductNum(productNum);
            product.setProductStatus(Integer.parseInt(productStatus));
            product.setProductPrice(Double.parseDouble(productPrice));
            list.add(product);
        }
        indexReader.close();
        return list;
    }
}
