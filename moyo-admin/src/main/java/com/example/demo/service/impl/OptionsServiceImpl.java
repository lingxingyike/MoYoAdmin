package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.OptionsMapper;
import com.example.demo.model.Options;
import com.example.demo.model.Options;
import com.example.demo.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class OptionsServiceImpl implements IOptionsService
{

    @Autowired
    private OptionsMapper optionsMapper;

    /**
     * 删除业务信息
     *
     * @param id 业务ID
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return optionsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param options 业务信息
     * @return 结果
     */
    @Override
    public int insert(Options options)
    {
        return optionsMapper.insert(options);
    }

    /**
     * 插入业务信息
     *
     * @param options 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(Options options)
    {
        return optionsMapper.insertSelective(options);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public Options selectByPrimaryKey(Long id)
    {
        return optionsMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param options 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(Options options)
    {
        return optionsMapper.updateByPrimaryKeySelective(options);
    }

    /**
     * 更新业务信息
     *
     * @param options 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(Options options)
    {
        return optionsMapper.updateByPrimaryKey(options);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param options 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<Options> selectOptionsList(Options options)
    {
        return optionsMapper.selectOptionsList(options);
    }

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    @Override
    public List<Options> selectOptionsAll()
    {
        return optionsMapper.selectOptionsAll();
    }

    /**
     * 通过题目ID查询题目信息
     *
     * @param id 题目ID
     * @return 角色对象信息
     */
    @Override
    public Options selectOptionsById(Long id)
    {
        return optionsMapper.selectOptionsById(id);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    @Override
    public List<Long> selectOptionsListById(Long id)
    {
        return optionsMapper.selectOptionsListById(id);
    }

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteOptionsById(Long id)
    {
        return optionsMapper.deleteOptionsById(id);
    }

    /**
     * 批量删除题目信息
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteOptionsByIds(Long[] ids)
    {
        return optionsMapper.deleteOptionsByIds(ids);
    }

    /**
     * 新增保存题目信息
     *
     * @param options 题目信息
     * @return 结果
     */
    @Override
    public int insertOptions(Options options)
    {
        return optionsMapper.insertOptions(options);
    }

    /**
     * 修改保存题目信息
     *
     * @param options 题目信息
     * @return 结果
     */
    @Override
    public int updateOptions(Options options)
    {
        return optionsMapper.updateOptions(options);
    }
}