package com.example.demo.mapper;

import com.example.demo.model.QrtzCronTriggers;
import com.example.demo.model.QrtzCronTriggersKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzCronTriggersMapper {
    int deleteByPrimaryKey(QrtzCronTriggersKey key);

    int insert(QrtzCronTriggers record);

    int insertSelective(QrtzCronTriggers record);

    QrtzCronTriggers selectByPrimaryKey(QrtzCronTriggersKey key);

    int updateByPrimaryKeySelective(QrtzCronTriggers record);

    int updateByPrimaryKey(QrtzCronTriggers record);
}
