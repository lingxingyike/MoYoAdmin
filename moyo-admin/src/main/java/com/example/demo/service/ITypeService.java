package com.example.demo.service;

import com.example.demo.model.Type;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface ITypeService
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
     * @param type 业务字段信息
     * @return 结果
     */
    public int insert(Type type);

    /**
     * 新增业务字段
     *
     * @param type 业务字段信息
     * @return 结果
     */
    public int insertSelective(Type type);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public Type selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param type 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(Type type);

    /**
     * 修改业务字段
     *
     * @param type 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(Type type);

    /*--------------------------------------------新增的-----------------------------------------------*/

    /**
     * 查询科目信息集合
     *
     * @param type 科目信息
     * @return 科目列表
     */
    public List<Type> selectTypeList(Type type);

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    public List<Type> selectTypeAll();

    /**
     * 通过科目ID查询科目信息
     *
     * @param typeId 科目ID
     * @return 角色对象信息
     */
    public Type selectTypeById(Long typeId);

    /**
     * 根据用户ID获取题型选择框列表
     *
     * @param typeId 用户ID
     * @return 选中题型ID列表
     */
    public List<Long> selectTypeListById(Long typeId);

    /**
     * 校验科目名称
     *
     * @param type 科目信息
     * @return 结果
     */
    public String checkTypeNameUnique(Type type);

    /**
     * 删除科目信息
     *
     * @param typeId 科目ID
     * @return 结果
     */
    public int deleteTypeById(Long typeId);

    /**
     * 批量删除科目信息
     *
     * @param typeIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteTypeByIds(Long[] typeIds);

    /**
     * 新增保存科目信息
     *
     * @param type 科目信息
     * @return 结果
     */
    public int insertType(Type type);

    /**
     * 修改保存科目信息
     *
     * @param type 科目信息
     * @return 结果
     */
    public int updateType(Type type);
}
