package com.example.demo.mapper;

import com.example.demo.model.QrtzPausedTriggerGrpsKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzPausedTriggerGrpsMapper {
    int deleteByPrimaryKey(QrtzPausedTriggerGrpsKey key);

    int insert(QrtzPausedTriggerGrpsKey record);

    int insertSelective(QrtzPausedTriggerGrpsKey record);
}
