package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.model.Question;
import com.example.demo.model.ms.QuestionOptions;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface IQuestionService
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
     * @param question 业务字段信息
     * @return 结果
     */
    public int insert(Question question);

    /**
     * 新增业务字段
     *
     * @param question 业务字段信息
     * @return 结果
     */
    public int insertSelective(Question question);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public Question selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param question 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(Question question);

    /**
     * 修改业务字段
     *
     * @param question 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(Question question);
    
    
    /*----------------------------------新增的-----------------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param question 题目信息
     * @return 题目列表
     */
    public List<Question> selectQuestionList(Question question);
    
    /**
     * 查询题目数据集合（包括选项数据）
     *
     * @param question 题目信息
     * @return 题目数据集合
     */
    public List<QuestionOptions> selectQuestionOptionsList(Question question);

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    public List<Question> selectQuestionAll();

    /**
     * 通过题目ID查询题目信息
     *
     * @param questionId 题目ID
     * @return 角色对象信息
     */
    public Question selectQuestionById(Long questionId);

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    public List<Long> selectQuestionListById(Long id);

    /**
     * 校验题目名称
     *
     * @param question 题目信息
     * @return 结果
     */
    public String checkQuestionTopicUnique(Question question);

    /**
     * 删除题目信息
     *
     * @param questionId 题目ID
     * @return 结果
     */
    public int deleteQuestionById(Long questionId);

    /**
     * 批量删除题目信息
     *
     * @param questionIds 需要删除的题目ID
     * @return 结果
     */
    public int deleteQuestionByIds(Long[] questionIds);

    /**
     * 新增保存题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改保存题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    public int updateQuestion(Question question);
}
