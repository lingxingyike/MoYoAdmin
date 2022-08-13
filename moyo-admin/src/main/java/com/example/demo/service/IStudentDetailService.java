package com.example.demo.service;

import com.example.demo.model.StudentDetail;
import com.example.demo.model.StudentDetail;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface IStudentDetailService
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
     * @param studentDetail 业务字段信息
     * @return 结果
     */
    public int insert(StudentDetail studentDetail);

    /**
     * 新增业务字段
     *
     * @param studentDetail 业务字段信息
     * @return 结果
     */
    public int insertSelective(StudentDetail studentDetail);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public StudentDetail selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param studentDetail 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(StudentDetail studentDetail);

    /**
     * 修改业务字段
     *
     * @param studentDetail 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(StudentDetail studentDetail);


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
     * @param name 用户名
     * @return 结果
     */
    public List<StudentDetail> selectStudentDetailByStudentName(String name);

    /**
     * 校验学员名称
     *
     * @param identity 身份证
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
     * 通过学员ID查询学员使用数量
     *
     * @param studentId 学员ID
     * @return 结果
     */
    public int countStudentDetailById(Long studentId);
}
