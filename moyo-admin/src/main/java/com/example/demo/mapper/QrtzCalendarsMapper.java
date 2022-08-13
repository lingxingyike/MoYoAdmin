package com.example.demo.mapper;

import com.example.demo.model.QrtzCalendars;
import com.example.demo.model.QrtzCalendarsKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzCalendarsMapper {
    int deleteByPrimaryKey(QrtzCalendarsKey key);

    int insert(QrtzCalendars record);

    int insertSelective(QrtzCalendars record);

    QrtzCalendars selectByPrimaryKey(QrtzCalendarsKey key);

    int updateByPrimaryKeySelective(QrtzCalendars record);

    int updateByPrimaryKeyWithBLOBs(QrtzCalendars record);
}
