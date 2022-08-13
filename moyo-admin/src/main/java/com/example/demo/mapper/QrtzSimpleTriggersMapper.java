package com.example.demo.mapper;

import com.example.demo.model.QrtzSimpleTriggers;
import com.example.demo.model.QrtzSimpleTriggersKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzSimpleTriggersMapper {
    int deleteByPrimaryKey(QrtzSimpleTriggersKey key);

    int insert(QrtzSimpleTriggers record);

    int insertSelective(QrtzSimpleTriggers record);

    QrtzSimpleTriggers selectByPrimaryKey(QrtzSimpleTriggersKey key);

    int updateByPrimaryKeySelective(QrtzSimpleTriggers record);

    int updateByPrimaryKey(QrtzSimpleTriggers record);
}
