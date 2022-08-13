package com.example.demo.service;

import com.example.demo.model.StudentUser;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface IStudentUserService
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
     * @param studentUser 业务字段信息
     * @return 结果
     */
    public int insert(StudentUser studentUser);

    /**
     * 新增业务字段
     *
     * @param studentUser 业务字段信息
     * @return 结果
     */
    public int insertSelective(StudentUser studentUser);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public StudentUser selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param studentUser 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(StudentUser studentUser);

    /**
     * 修改业务字段
     *
     * @param studentUser 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(StudentUser studentUser);



    /**
     * 查询学员信息集合
     *
     * @param studentUser 学员信息
     * @return 学员列表
     */
    public List<StudentUser> selectStudentUserList(StudentUser studentUser);

    /**
     * 查询所有学员
     *
     * @return 学员列表
     */
    public List<StudentUser> selectStudentUserAll();

    /**
     * 通过学员ID查询学员信息
     *
     * @param studentUserId 学员ID
     * @return 角色对象信息
     */
    public StudentUser selectStudentUserById(Long studentUserId);

    /**
     * 根据用户ID获取学员选择框列表
     *
     * @param userId 用户ID
     * @return 选中学员ID列表
     */
    public List<Long> selectStudentUserListByUserId(Long userId);

    /**
     * 校验学员名称
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public String checkStudentUserNameUnique(StudentUser studentUser);

    /**
     * 校验学员编码
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public String checkStudentUserNoUnique(StudentUser studentUser);

    /**
     * 通过学员ID查询学员使用数量
     *
     * @param studentUserId 学员ID
     * @return 结果
     */
    public int countUserStudentUserById(Long studentUserId);

    /**
     * 删除学员信息
     *
     * @param studentUserId 学员ID
     * @return 结果
     */
    public int deleteStudentUserById(Long studentUserId);

    /**
     * 批量删除学员信息
     *
     * @param studentUserIds 需要删除的学员ID
     * @return 结果
     */
    public int deleteStudentUserByIds(Long[] studentUserIds);

    /**
     * 新增保存学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public int insertStudentUser(StudentUser studentUser);

    /**
     * 修改保存学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public int updateStudentUser(StudentUser studentUser);
}
