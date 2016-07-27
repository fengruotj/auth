package com.springmvc.auth.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dello on 2016/7/27.
 * 实体类的基类
 */
public class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static <T extends BaseEntity> Map<Long,T> idEntityMap(Collection<T> list){
        Map<Long,T> map=new HashMap<>();

        if(null==list||0==list.size()){
            return map;
        }

        for(T entity:list){
            map.put(entity.getId(),entity);
        }
        return map;
    }
}
