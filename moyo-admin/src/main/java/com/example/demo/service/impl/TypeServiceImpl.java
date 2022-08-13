package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.model.Type;
import com.example.demo.model.Type;
import com.example.demo.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class TypeServiceImpl implements ITypeService
{

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 删除业务信息
     *
     * @param id 业务ID
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return typeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param type 业务信息
     * @return 结果
     */
    @Override
    public int insert(Type type)
    {
        return typeMapper.insert(type);
    }

    /**
     * 插入业务信息
     *
     * @param type 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(Type type)
    {
        return typeMapper.insertSelective(type);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public Type selectByPrimaryKey(Long id)
    {
        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param type 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(Type type)
    {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    /**
     * 更新业务信息
     *
     * @param type 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(Type type)
    {
        return typeMapper.updateByPrimaryKey(type);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题型信息集合
     *
     * @param type 题型信息
     * @return 题型信息集合
     */
    @Override
    public List<Type> selectTypeList(Type type)
    {
        return typeMapper.selectTypeList(type);
    }

    /**
     * 查询所有题型
     *
     * @return 题型列表
     */
    @Override
    public List<Type> selectTypeAll()
    {
        return typeMapper.selectTypeAll();
    }

    /**
     * 通过题型ID查询题型信息
     *
     * @param typeId 题型ID
     * @return 角色对象信息
     */
    @Override
    public Type selectTypeById(Long typeId)
    {
        return typeMapper.selectTypeById(typeId);
    }

    /**
     * 根据题型ID获取题型选择框列表
     *
     * @param id 题型ID
     * @return 选中题型ID列表
     */
    @Override
    public List<Long> selectTypeListById(Long id)
    {
        return typeMapper.selectTypeListById(id);
    }

    /**
     * 校验题型名称是否唯一
     *
     * @param type 题型信息
     * @return 结果
     */
    @Override
    public String checkTypeNameUnique(Type type)
    {
        Long typeId = StringUtils.isNull(type.getId()) ? -1L : type.getId();
        Type info = typeMapper.checkTypeNameUnique(type.getName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != typeId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过题型ID查询题型使用数量
     *
     * @param typeId 题型ID
     * @return 结果
     */
    public int countQuestionTypeById(Long typeId)
    {
        return questionMapper.countQuestionByTypeId(typeId);
    }

    /**
     * 删除题型信息
     *
     * @param typeId 题型ID
     * @return 结果
     */
    @Override
    public int deleteTypeById(Long typeId)
    {
        return typeMapper.deleteTypeById(typeId);
    }

    /**
     * 批量删除题型信息
     *
     * @param typeIds 需要删除的题型ID
     * @return 结果
     */
    @Override
    public int deleteTypeByIds(Long[] typeIds)
    {
        for (Long typeId : typeIds)
        {
            Type type = selectTypeById(typeId);
            if (countQuestionTypeById(typeId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", type.getName()));
            }
        }
        return typeMapper.deleteTypeByIds(typeIds);
    }

    /**
     * 新增保存题型信息
     *
     * @param type 题型信息
     * @return 结果
     */
    @Override
    public int insertType(Type type)
    {
        return typeMapper.insertType(type);
    }

    /**
     * 修改保存题型信息
     *
     * @param type 题型信息
     * @return 结果
     */
    @Override
    public int updateType(Type type)
    {
        return typeMapper.updateType(type);
    }

}