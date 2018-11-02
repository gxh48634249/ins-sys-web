package com.ins.sys.tools;

import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 约定service方法实现
 * @param <T>
 */
public interface SimpleService<T> {

    @Transactional
    T save(T t)throws Exception;

    @Transactional
    Long remove(T t)throws Exception;

    @Transactional
    T modify(T t)throws Exception;

    @Transactional
    Page<T> findByPage(T t, PageInfo pageInfo)throws Exception;

    @Transactional
    List<T> findAll()throws Exception;

    @Transactional
    T findById(String id)throws Exception;
}
