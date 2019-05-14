package com.easy.store.repository.jpa.repository;

import com.amcjt.jbhim.repository.jpa.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuRepository extends JpaRepository<SysMenuEntity, String> {

}
