package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.OptionsMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Question;
import com.example.demo.model.ms.QuestionOptions;
import com.example.demo.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class QuestionServiceImpl implements IQuestionService
{

    @Autowired
    private QuestionMapper questionMapper;

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
        return questionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param question 业务信息
     * @return 结果
     */
    @Override
    public int insert(Question question)
    {
        return questionMapper.insert(question);
    }

    /**
     * 插入业务信息
     *
     * @param question 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(Question question)
    {
        return questionMapper.insertSelective(question);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public Question selectByPrimaryKey(Long id)
    {
        return questionMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param question 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(Question question)
    {
        return questionMapper.updateByPrimaryKeySelective(question);
    }

    /**
     * 更新业务信息
     *
     * @param question 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(Question question)
    {
        return questionMapper.updateByPrimaryKey(question);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param question 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<Question> selectQuestionList(Question question)
    {
        return questionMapper.selectQuestionList(question);
    }

    /**
     * 查询题目信息集合(包含详情)
     *
     * @param question 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<QuestionOptions> selectQuestionOptionsList(Question question)
    {
        return questionMapper.selectQuestionOptionsList(question);
    }

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    @Override
    public List<Question> selectQuestionAll()
    {
        return questionMapper.selectQuestionAll();
    }

    /**
     * 通过题目ID查询题目信息
     *
     * @param id 题目ID
     * @return 角色对象信息
     */
    @Override
    public Question selectQuestionById(Long id)
    {
        return questionMapper.selectQuestionById(id);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    @Override
    public List<Long> selectQuestionListById(Long id)
    {
        return questionMapper.selectQuestionListById(id);
    }

    /**
     * 校验题目名称是否唯一
     *
     * @param question 题目信息
     * @return 结果
     */
    @Override
    public String checkQuestionTopicUnique(Question question)
    {
        Long id = StringUtils.isNull(question.getId()) ? -1L : question.getId();
        Question info = questionMapper.checkQuestionTopicUnique(question.getTopic());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过题目ID查询题目使用数量
     *
     * @param id 题目ID
     * @return 结果
     */
    public int countOptionsByQuestionId(Long id)
    {
        return optionsMapper.countOptionsByQuestionId(id);
    }

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteQuestionById(Long id)
    {
        return questionMapper.deleteQuestionById(id);
    }

    /**
     * 批量删除题目信息
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteQuestionByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            Question question = selectQuestionById(id);
            if (countOptionsByQuestionId(id) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", question.getTopic()));
            }
        }
        return questionMapper.deleteQuestionByIds(ids);
    }

    /**
     * 新增保存题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    @Override
    public int insertQuestion(Question question)
    {
        return questionMapper.insertQuestion(question);
    }

    /**
     * 修改保存题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    @Override
    public int updateQuestion(Question question)
    {
        return questionMapper.updateQuestion(question);
    }
}