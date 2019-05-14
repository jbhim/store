package com.easy.store.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class MailBean implements Serializable {
    //邮件接收人
    private String recipient;
    //邮件主题
    private String subject;
    //邮件内容
    private String content;
    //邮件内容html
    private String htmlContent;
    //附件 key: value => name: file/is
    private Map<String, Object> attachment;
}