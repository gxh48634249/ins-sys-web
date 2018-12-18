//package com.ins.sys.esentity.domain;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//
//public interface ENSRepository extends ElasticsearchRepository<ENSWeb,String> {
//
//    Page<ENSWeb> findENSWebByContent(String content, Pageable pageable)throws Exception;
//
//    Page<ENSWeb> findENSWebByContentAndContentIsContaining(String content, String contentContaining, Pageable pageable)throws Exception;
//
//}
