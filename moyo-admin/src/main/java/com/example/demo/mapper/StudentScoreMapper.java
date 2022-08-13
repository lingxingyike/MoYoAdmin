package com.example.demo.mapper;

import com.example.demo.model.StudentScore;
import com.example.demo.model.StudentScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentScore record);

    int insertSelective(StudentScore record);

    StudentScore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentScore record);

    int updateByPrimaryKey(StudentScore record);

    int countStudentScoreById(Long studentId);


    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询成绩数据集合
     *
     * @param studentScore 成绩信息
     * @return 成绩数据集合
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
     * 根据用户ID获取成绩选择框列表
     *
     * @param userId 用户ID
     * @return 选中成绩ID列表
     */
    public List<Long> selectStudentScoreListById(Long userId);

    /**
     * 查询用户所属成绩组
     *
     * @param topic 用户名
     * @return 结果
     */
    public List<StudentScore> selectStudentScoresByName(String topic);

    /**
     * 校验成绩名称
     *
     * @param studentScoreName 成绩名称
     * @return 结果
     */
    public StudentScore checkStudentScoreUnique(String studentScoreName);

    /**
     * 修改成绩信息
     *
     * @param studentScore 成绩信息
     * @return 结果
     */
    public int updateStudentScore(StudentScore studentScore);

    /**
     * 新增成绩信息
     *
     * @param studentScore 成绩信息
     * @return 结果
     */
    public int insertStudentScore(StudentScore studentScore);

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