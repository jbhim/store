package com.easy.store.utils;

import com.amcjt.jbhim.model.TreeModel;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    public static List<TreeModel> buildTree(List<TreeModel> buildList) {
        List<TreeModel> treeList = new ArrayList<>();
        for (TreeModel childrenMenu : buildList) {
            boolean flag = false;
            for (TreeModel parentMenu : buildList) {
                if (parentMenu.getId().equals(childrenMenu.getParentId())) {
                    flag = true;
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(childrenMenu);
                    break;
                }
            }
            if (!flag) {
                treeList.add(childrenMenu);
            }
        }
        return treeList;
    }
}
