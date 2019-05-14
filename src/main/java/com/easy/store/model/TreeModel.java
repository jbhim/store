package com.easy.store.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeModel {
    private String id;
    private String name;
    private String parentId;
    private String type;
    private List<TreeModel> children = new ArrayList<>();
}
