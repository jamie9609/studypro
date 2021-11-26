package com.jamie.javastudy.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @PackageName: com.jamie.study.collection
 * @ClassName: collectionStudy
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/20 12:38 下午
 */
public class CollectionStudy {

    public static void main(String[] args) {

        Collection<Object> collection = new ArrayList<>();
        collection.add("ssss");
        collection.add("china");
        collection.add("java");
        System.out.println(collection);
        collection.forEach(e -> System.out.println(e));

        System.out.println(UUID.randomUUID());

    }
}
