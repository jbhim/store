package com.easy.store.repository.mybatis.mapper;

import com.amcjt.jbhim.repository.mybatis.entity.DocumentDirectory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentMapper {
    List<DocumentDirectory> getDocumentDir();
}
