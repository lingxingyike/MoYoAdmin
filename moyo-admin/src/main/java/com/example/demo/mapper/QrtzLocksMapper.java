package com.example.demo.mapper;

import com.example.demo.model.QrtzLocksKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzLocksMapper {
    int deleteByPrimaryKey(QrtzLocksKey key);

    int insert(QrtzLocksKey record);

    int insertSelective(QrtzLocksKey record);
}
