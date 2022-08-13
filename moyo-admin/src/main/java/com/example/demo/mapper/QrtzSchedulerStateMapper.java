package com.example.demo.mapper;

import com.example.demo.model.QrtzSchedulerState;
import com.example.demo.model.QrtzSchedulerStateKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzSchedulerStateMapper {
    int deleteByPrimaryKey(QrtzSchedulerStateKey key);

    int insert(QrtzSchedulerState record);

    int insertSelective(QrtzSchedulerState record);

    QrtzSchedulerState selectByPrimaryKey(QrtzSchedulerStateKey key);

    int updateByPrimaryKeySelective(QrtzSchedulerState record);

    int updateByPrimaryKey(QrtzSchedulerState record);
}
