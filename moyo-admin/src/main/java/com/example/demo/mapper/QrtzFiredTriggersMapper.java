package com.example.demo.mapper;

import com.example.demo.model.QrtzFiredTriggers;
import com.example.demo.model.QrtzFiredTriggersKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzFiredTriggersMapper {
    int deleteByPrimaryKey(QrtzFiredTriggersKey key);

    int insert(QrtzFiredTriggers record);

    int insertSelective(QrtzFiredTriggers record);

    QrtzFiredTriggers selectByPrimaryKey(QrtzFiredTriggersKey key);

    int updateByPrimaryKeySelective(QrtzFiredTriggers record);

    int updateByPrimaryKey(QrtzFiredTriggers record);
}
