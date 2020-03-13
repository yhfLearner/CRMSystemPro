package com.hwua.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

@Configuration
public class LuceneConfig {
    public static final String INDEX_PATH = "d:\\indexdatabase\\crmindex";

    @Bean
    public Directory directory() throws Exception{
        File dir = new File(INDEX_PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return FSDirectory.open(dir.toPath());
    }
    @Bean
    public Analyzer IkAnalyzer() throws Exception{
        return new IKAnalyzer();
    }
    @Bean
    public IndexWriter indexWriter(Directory directory,Analyzer analyzer) throws Exception{
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        return indexWriter;

    }
}
