package com.example.demo.mapper;

import com.example.demo.model.QrtzTriggers;
import com.example.demo.model.QrtzTriggersKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzTriggersMapper {
    int deleteByPrimaryKey(QrtzTriggersKey key);

    int insert(QrtzTriggers record);

    int insertSelective(QrtzTriggers record);

    QrtzTriggers selectByPrimaryKey(QrtzTriggersKey key);

    int updateByPrimaryKeySelective(QrtzTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzTriggers record);

    int updateByPrimaryKey(QrtzTriggers record);
}
