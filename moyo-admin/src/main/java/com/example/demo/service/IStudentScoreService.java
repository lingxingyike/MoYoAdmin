package com.example.demo.service;

import com.example.demo.model.StudentScore;
import com.example.demo.model.StudentScore;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface IStudentScoreService
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
     * @param studentScore 业务字段信息
     * @return 结果
     */
    public int insert(StudentScore studentScore);

    /**
     * 新增业务字段
     *
     * @param studentScore 业务字段信息
     * @return 结果
     */
    public int insertSelective(StudentScore studentScore);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public StudentScore selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param studentScore 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(StudentScore studentScore);

    /**
     * 修改业务字段
     *
     * @param studentScore 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(StudentScore studentScore);



    /*----------------------------------新增的-----------------------------------------*/

    /**
     * 查询成绩信息集合
     *
     * @param studentScore 成绩信息
     * @return 成绩列表
     */
    public List<StudentScore> selectStudentScoreList(StudentScore studentScore);

    /**
     * 查询所有成绩
     *
     * @return 成绩列表
     */
    public List<StudentScore> selectStudentScoreAll();

    /**
     * 通过成绩ID查询成绩信息
     *
     * @param studentScoreId 成绩ID
     * @return 角色对象信息
     */
    public StudentScore selectStudentScoreById(Long studentScoreId);

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    public List<Long> selectStudentScoreListById(Long id);

    /**
     * 删除成绩信息
     *
     * @param studentScoreId 成绩ID
     * @return 结果
     */
    public int deleteStudentScoreById(Long studentScoreId);

    /**
     * 批量删除成绩信息
     *
     * @param studentScoreIds 需要删除的成绩ID
     * @return 结果
     */
    public int deleteStudentScoreByIds(Long[] studentScoreIds);

    /**
     * 新增保存成绩信息
     *
     * @param studentScore 成绩信息
     * @return 结果
     */
    public int insertStudentScore(StudentScore studentScore);

    /**
     * 修改保存成绩信息
     *
     * @param studentScore 成绩信息
     * @return 结果
     */
    public int updateStudentScore(StudentScore studentScore);
    
    /**
     * 通过学员ID查询学员使用数量
     *
     * @param studentId 学员ID
     * @return 结果
     */
    public int countStudentScoreById(Long studentId);
}
