package com.example.demo.service.impl;

import com.example.demo.mapper.StudentScoreMapper;
import com.example.demo.model.StudentScore;
import com.example.demo.service.IStudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class StudentScoreServiceImpl implements IStudentScoreService
{

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    /**
     * 删除业务信息
     *
     * @param id 业务ID
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return studentScoreMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param studentScore 业务信息
     * @return 结果
     */
    @Override
    public int insert(StudentScore studentScore)
    {
        return studentScoreMapper.insert(studentScore);
    }

    /**
     * 插入业务信息
     *
     * @param studentScore 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(StudentScore studentScore)
    {
        return studentScoreMapper.insertSelective(studentScore);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public StudentScore selectByPrimaryKey(Long id)
    {
        return studentScoreMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param studentScore 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(StudentScore studentScore)
    {
        return studentScoreMapper.updateByPrimaryKeySelective(studentScore);
    }

    /**
     * 更新业务信息
     *
     * @param studentScore 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(StudentScore studentScore)
    {
        return studentScoreMapper.updateByPrimaryKey(studentScore);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param studentScore 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<StudentScore> selectStudentScoreList(StudentScore studentScore)
    {
        return studentScoreMapper.selectStudentScoreList(studentScore);
    }

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    @Override
    public List<StudentScore> selectStudentScoreAll()
    {
        return studentScoreMapper.selectStudentScoreAll();
    }

    /**
     * 通过题目ID查询题目信息
     *
     * @param id 题目ID
     * @return 角色对象信息
     */
    @Override
    public StudentScore selectStudentScoreById(Long id)
    {
        return studentScoreMapper.selectStudentScoreById(id);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    @Override
    public List<Long> selectStudentScoreListById(Long id)
    {
        return studentScoreMapper.selectStudentScoreListById(id);
    }

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteStudentScoreById(Long id)
    {
        return studentScoreMapper.deleteStudentScoreById(id);
    }

    /**
     * 批量删除题目信息
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteStudentScoreByIds(Long[] ids)
    {
        return studentScoreMapper.deleteStudentScoreByIds(ids);
    }

    /**
     * 新增保存题目信息
     *
     * @param studentScore 题目信息
     * @return 结果
     */
    @Override
    public int insertStudentScore(StudentScore studentScore)
    {
        return studentScoreMapper.insertStudentScore(studentScore);
    }

    /**
     * 修改保存题目信息
     *
     * @param studentScore 题目信息
     * @return 结果
     */
    @Override
    public int updateStudentScore(StudentScore studentScore)
    {
        return studentScoreMapper.updateStudentScore(studentScore);
    }

    /**
     * 通过学员ID查询学员成绩使用数量
     *
     * @param studentId 学员ID
     * @return 结果
     */
    @Override
    public int countStudentScoreById(Long studentId)
    {
        return studentScoreMapper.countStudentScoreById(studentId);
    }
}