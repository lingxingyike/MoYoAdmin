package com.example.demo.mapper;

import com.example.demo.model.QrtzJobDetails;
import com.example.demo.model.QrtzJobDetailsKey;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzJobDetailsMapper {
    int deleteByPrimaryKey(QrtzJobDetailsKey key);

    int insert(QrtzJobDetails record);

    int insertSelective(QrtzJobDetails record);

    QrtzJobDetails selectByPrimaryKey(QrtzJobDetailsKey key);

    int updateByPrimaryKeySelective(QrtzJobDetails record);

    int updateByPrimaryKeyWithBLOBs(QrtzJobDetails record);

    int updateByPrimaryKey(QrtzJobDetails record);
}
