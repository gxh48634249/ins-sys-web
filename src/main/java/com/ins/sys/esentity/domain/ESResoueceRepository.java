package com.ins.sys.esentity.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESResoueceRepository extends ElasticsearchRepository<ESResource,String> {

    Iterable<ESResource> findESResourceByContentLike(String contemt)throws Exception;

    Page<ESResource> findByContentWithin(Pageable pageable,String... content) throws Exception;

    Page<ESResource> findByContentIn(Pageable pageable, List<String> list) throws Exception;
    Page<ESResource> findByContentIsIn(Pageable pageable, List<String> list) throws Exception;
    Page<ESResource> findByContentContains(Pageable pageable, String...content) throws Exception;

    Page<ESResource> findESResourceByContent(String content,Pageable pageable)throws Exception;

    Page<ESResource> findESResourceByContentAndContentAndContent(String content,String content1,String content2,Pageable pageable)throws Exception;

    Page<ESResource> findESResourceByContentInAndAndContent(List<String> list,String content,Pageable pageable)throws Exception;

    @Query("{'bool':{'must':[{'field':{'content':'?0'}},{'field':{'content':'?1'}},{'field':{'content':'?2'}}]}}")
    Page<ESResource> findESResourceByContents(String content1,String content2,String content3,Pageable pageable)throws Exception;

    Page<ESResource> findESResourceByContentAndContentIsNotIn(String content,List<String> not,Pageable pageable)throws Exception;

    Page<ESResource> findESResourceByContentAndContentIsNot(String content,String not,Pageable pageable)throws Exception;

}
