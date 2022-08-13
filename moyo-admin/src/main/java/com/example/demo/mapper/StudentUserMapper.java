package com.example.demo.mapper;

import com.example.demo.model.StudentUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentUser record);

    int insertSelective(StudentUser record);

    StudentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentUser record);

    int updateByPrimaryKey(StudentUser record);

/*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询学员数据集合
     *
     * @param studentUser 学员信息
     * @return 学员数据集合
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
     * 查询用户所属学员组
     *
     * @param username 用户名
     * @return 结果
     */
    public List<StudentUser> selectStudentUsersByUserName(String username);

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
     * 修改学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public int updateStudentUser(StudentUser studentUser);

    /**
     * 新增学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    public int insertStudentUser(StudentUser studentUser);

    /**
     * 校验学员名称
     *
     * @param studentUserName 学员名称
     * @return 结果
     */
    public StudentUser checkStudentUserNameUnique(String studentUserName);

    /**
     * 校验学员编码
     *
     * @param studentUserCode 学员编码
     * @return 结果
     */
    public StudentUser checkStudentUserNoUnique(String studentUserCode);
}