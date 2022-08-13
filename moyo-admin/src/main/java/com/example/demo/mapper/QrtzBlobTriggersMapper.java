package com.example.demo.mapper;

import com.example.demo.model.QrtzBlobTriggers;
import com.example.demo.model.QrtzBlobTriggersKey;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QrtzBlobTriggersMapper {
    int deleteByPrimaryKey(QrtzBlobTriggersKey key);

    int insert(QrtzBlobTriggers record);

    int insertSelective(QrtzBlobTriggers record);

    QrtzBlobTriggers selectByPrimaryKey(QrtzBlobTriggersKey key);

    int updateByPrimaryKeySelective(QrtzBlobTriggers record);

    int updateByPrimaryKeyWithBLOBs(QrtzBlobTriggers record);
}
