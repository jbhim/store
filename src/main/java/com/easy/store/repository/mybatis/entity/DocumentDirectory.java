package com.easy.store.repository.mybatis.entity;

import lombok.Data;

@Data
public class DocumentDirectory {
    //`ID` int(11) NOT NULL AUTO_INCREMENT,
//`NAME` varchar(255) DEFAULT NULL COMMENT '名称',
//`PAREBT_ID` varchar(255) DEFAULT NULL COMMENT '父节点ID',
//`STATE` int(255) DEFAULT NULL COMMENT '状态',
//`CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
//`CREATE_USERID` varchar(255) DEFAULT NULL COMMENT '创建人用户ID',
//`ISSYSTEM` int(1) DEFAULT NULL COMMENT '是否系统',
//`LEVEL` varchar(255) DEFAULT NULL COMMENT '目录级别',
//`tenantId` varchar(255) NOT NULL DEFAULT '0',
    private int id;
    private String name;
    private String parentId;
    private String state;
    private String createTime;
    private String createUserId;
    private String issystem;
    private String level;
}
