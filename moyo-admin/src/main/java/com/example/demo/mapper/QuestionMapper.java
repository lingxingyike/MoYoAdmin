package com.example.demo.mapper;

import com.example.demo.model.Question;
import com.example.demo.model.Question;
import com.example.demo.model.ms.QuestionOptions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);



    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询题目数据集合
     *
     * @param question 题目信息
     * @return 题目数据集合
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
     * @param id 题目ID
     * @return 角色对象信息
     */
    public Question selectQuestionById(Long id);

    /**
     * 根据用户ID获取题目选择框列表
     *
     * @param userId 用户ID
     * @return 选中题目ID列表
     */
    public List<Long> selectQuestionListById(Long userId);

    /**
     * 查询用户所属题目组
     *
     * @param topic 用户名
     * @return 结果
     */
    public List<Question> selectQuestionsByTopicName(String topic);

    /**
     * 校验题目名称
     *
     * @param questionName 题目名称
     * @return 结果
     */
    public Question checkQuestionTopicUnique(String questionName);

    /**
     * 修改题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    public int updateQuestion(Question question);

    /**
     * 新增题目信息
     *
     * @param question 题目信息
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    public int deleteQuestionById(Long id);

    /**
     * 批量删除题目信息
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    public int deleteQuestionByIds(Long[] ids);

    /**
     * 通过题型ID查询题型使用数量
     *
     * @param typeId 题型ID
     * @return 结果
     */
    int countQuestionByTypeId(Long typeId);
    /**
     * 通过题型ID查询题型使用数量
     *
     * @param levelId 科目ID
     * @return 结果
     */
    int countQuestionByLevelId(Long levelId);
    /**
     * 通过题型ID查询题型使用数量
     *
     * @param licenseId 驾驶证类型ID
     * @return 结果
     */
    int countQuestionByLicenseId(Long licenseId);
}