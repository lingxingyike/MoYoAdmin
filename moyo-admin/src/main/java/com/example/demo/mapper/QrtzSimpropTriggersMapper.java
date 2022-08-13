package com.example.demo.mapper;

import com.example.demo.model.QrtzSimpropTriggers;
import com.example.demo.model.QrtzSimpropTriggersKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzSimpropTriggersMapper {
    int deleteByPrimaryKey(QrtzSimpropTriggersKey key);

    int insert(QrtzSimpropTriggers record);

    int insertSelective(QrtzSimpropTriggers record);

    QrtzSimpropTriggers selectByPrimaryKey(QrtzSimpropTriggersKey key);

    int updateByPrimaryKeySelective(QrtzSimpropTriggers record);

    int updateByPrimaryKey(QrtzSimpropTriggers record);
}
