package com.example.demo.mapper;

import com.example.demo.model.StudentDetail;
import com.example.demo.model.StudentDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentDetail record);

    int insertSelective(StudentDetail record);

    StudentDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentDetail record);

    int updateByPrimaryKey(StudentDetail record);


    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询学员数据集合
     *
     * @param detail 学员信息
     * @return 学员数据集合
     */
    public List<StudentDetail> selectStudentDetailList(StudentDetail detail);

    /**
     * 查询所有学员
     *
     * @return 学员列表
     */
    public List<StudentDetail> selectStudentDetailAll();

    /**
     * 通过学员ID查询学员信息
     *
     * @param id 学员ID
     * @return 角色对象信息
     */
    public StudentDetail selectStudentDetailById(Long id);

    /**
     * 根据用户ID获取学员选择框列表
     *
     * @param userId 用户ID
     * @return 选中学员ID列表
     */
    public List<Long> selectStudentDetailListById(Long userId);

    /**
     * 查询用户所属学员组
     *
     * @param name 学员名
     * @return 结果
     */
    public List<StudentDetail> selectStudentDetailByStudentName(String name);

    /**
     * 校验身份证号码
     *
     * @param identity 身份证号码
     * @return 结果
     */
    public StudentDetail checkStudentDetailIdentityUnique(String identity);

    /**
     * 修改学员信息
     *
     * @param detail 学员信息
     * @return 结果
     */
    public int updateStudentDetail(StudentDetail detail);

    /**
     * 新增学员信息
     *
     * @param detail 学员信息
     * @return 结果
     */
    public int insertStudentDetail(StudentDetail detail);

    /**
     * 删除学员信息
     *
     * @param id 学员ID
     * @return 结果
     */
    public int deleteStudentDetailById(Long id);

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员ID
     * @return 结果
     */
    public int deleteStudentDetailByIds(Long[] ids);

    /**
     * 通过学员ID查询学员详情使用数量
     *
     * @param studentId 选项ID
     * @return 结果
     */
    int countStudentDetailById(Long studentId);
}