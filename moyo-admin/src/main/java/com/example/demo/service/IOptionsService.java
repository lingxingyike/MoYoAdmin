package com.example.demo.service;

import com.example.demo.model.Options;
import com.example.demo.model.Options;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface IOptionsService
{
    /**
     * 删除业务字段信息
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public int deleteByPrimaryKey(Long id);

    /**
     * 新增业务字段
     *
     * @param options 业务字段信息
     * @return 结果
     */
    public int insert(Options options);

    /**
     * 新增业务字段
     *
     * @param options 业务字段信息
     * @return 结果
     */
    public int insertSelective(Options options);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public Options selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param options 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(Options options);

    /**
     * 修改业务字段
     *
     * @param options 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(Options options);



    /*----------------------------------新增的-----------------------------------------*/

    /**
     * 查询科目信息集合
     *
     * @param option 科目信息
     * @return 科目列表
     */
    public List<Options> selectOptionsList(Options option);

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    public List<Options> selectOptionsAll();

    /**
     * 通过科目ID查询科目信息
     *
     * @param optionId 科目ID
     * @return 角色对象信息
     */
    public Options selectOptionsById(Long optionId);

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    public List<Long> selectOptionsListById(Long id);

    /**
     * 删除科目信息
     *
     * @param optionId 科目ID
     * @return 结果
     */
    public int deleteOptionsById(Long optionId);

    /**
     * 批量删除科目信息
     *
     * @param optionIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteOptionsByIds(Long[] optionIds);

    /**
     * 新增保存科目信息
     *
     * @param option 科目信息
     * @return 结果
     */
    public int insertOptions(Options option);

    /**
     * 修改保存科目信息
     *
     * @param option 科目信息
     * @return 结果
     */
    public int updateOptions(Options option);
}
