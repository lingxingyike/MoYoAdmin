package com.example.demo.service;

import com.example.demo.model.Level;
import com.example.demo.model.Level;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface ILevelService
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
     * @param level 业务字段信息
     * @return 结果
     */
    public int insert(Level level);

    /**
     * 新增业务字段
     *
     * @param level 业务字段信息
     * @return 结果
     */
    public int insertSelective(Level level);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public Level selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param level 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(Level level);

    /**
     * 修改业务字段
     *
     * @param level 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(Level level);
    
    /*------------------------------新增的---------------------------*/

    /**
     * 查询科目信息集合
     *
     * @param level 科目信息
     * @return 科目列表
     */
    public List<Level> selectLevelList(Level level);

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    public List<Level> selectLevelAll();

    /**
     * 通过科目ID查询科目信息
     *
     * @param levelId 科目ID
     * @return 角色对象信息
     */
    public Level selectLevelById(Long levelId);
    /**
     * 通过科目ID查询科目信息
     *
     * @param id 科目ID
     * @return 角色对象信息
     */
    public List<Long> selectLevelListById(Long id);

    /**
     * 校验科目名称
     *
     * @param level 科目信息
     * @return 结果
     */
    public String checkLevelNameUnique(Level level);

    /**
     * 通过科目ID查询科目使用数量
     *
     * @param levelId 科目ID
     * @return 结果
     */
    public int countQuestionByLevelId(Long levelId);

    /**
     * 删除科目信息
     *
     * @param levelId 科目ID
     * @return 结果
     */
    public int deleteLevelById(Long levelId);

    /**
     * 批量删除科目信息
     *
     * @param levelIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteLevelByIds(Long[] levelIds);

    /**
     * 新增保存科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    public int insertLevel(Level level);

    /**
     * 修改保存科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    public int updateLevel(Level level);
}
