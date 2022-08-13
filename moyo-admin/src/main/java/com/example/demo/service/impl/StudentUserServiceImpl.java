package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.StudentDetailMapper;
import com.example.demo.mapper.StudentScoreMapper;
import com.example.demo.mapper.StudentUserMapper;
import com.example.demo.model.StudentUser;
import com.example.demo.service.IStudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class StudentUserServiceImpl implements IStudentUserService
{

    @Autowired
    private StudentUserMapper studentUserMapper;
    @Autowired
    private StudentDetailMapper studentDetailMapper;
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
        return studentUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param studentUser 业务信息
     * @return 结果
     */
    @Override
    public int insert(StudentUser studentUser)
    {
        return studentUserMapper.insert(studentUser);
    }

    /**
     * 插入业务信息
     *
     * @param studentUser 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(StudentUser studentUser)
    {
        return studentUserMapper.insertSelective(studentUser);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public StudentUser selectByPrimaryKey(Long id)
    {
        return studentUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param studentUser 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(StudentUser studentUser)
    {
        return studentUserMapper.updateByPrimaryKeySelective(studentUser);
    }

    /**
     * 更新业务信息
     *
     * @param studentUser 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(StudentUser studentUser)
    {
        return studentUserMapper.updateByPrimaryKey(studentUser);
    }


/*--------------------新增的-----------------------------------*/

    /**
     * 查询学员信息集合
     *
     * @param studentUser 学员信息
     * @return 学员信息集合
     */
    @Override
    public List<StudentUser> selectStudentUserList(StudentUser studentUser)
    {
        return studentUserMapper.selectStudentUserList(studentUser);
    }

    /**
     * 查询所有学员
     *
     * @return 学员列表
     */
    @Override
    public List<StudentUser> selectStudentUserAll()
    {
        return studentUserMapper.selectStudentUserAll();
    }

    /**
     * 通过学员ID查询学员信息
     *
     * @param studentUserId 学员ID
     * @return 角色对象信息
     */
    @Override
    public StudentUser selectStudentUserById(Long studentUserId)
    {
        return studentUserMapper.selectStudentUserById(studentUserId);
    }

    /**
     * 根据用户ID获取学员选择框列表
     *
     * @param userId 用户ID
     * @return 选中学员ID列表
     */
    @Override
    public List<Long> selectStudentUserListByUserId(Long userId)
    {
        return studentUserMapper.selectStudentUserListByUserId(userId);
    }

    /**
     * 校验学员名称是否唯一
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    @Override
    public String checkStudentUserNameUnique(StudentUser studentUser)
    {
        Long studentUserId = StringUtils.isNull(studentUser.getId()) ? -1L : studentUser.getId();
        StudentUser info = studentUserMapper.checkStudentUserNameUnique(studentUser.getUsername());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != studentUserId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验学员编码是否唯一
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    @Override
    public String checkStudentUserNoUnique(StudentUser studentUser)
    {
        Long studentUserId = StringUtils.isNull(studentUser.getId()) ? -1L : studentUser.getId();
        StudentUser info = studentUserMapper.checkStudentUserNoUnique(studentUser.getStudentNo());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != studentUserId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过学员ID查询学员使用数量
     *
     * @param studentUserId 学员ID
     * @return 结果
     */
    @Override
    public int countUserStudentUserById(Long studentUserId)
    {
        return studentScoreMapper.countStudentScoreById(studentUserId);
    }

    /**
     * 删除学员信息
     *
     * @param studentUserId 学员ID
     * @return 结果
     */
    @Override
    public int deleteStudentUserById(Long studentUserId)
    {
        return studentUserMapper.deleteStudentUserById(studentUserId);
    }

    /**
     * 批量删除学员信息
     *
     * @param studentUserIds 需要删除的学员ID
     * @return 结果
     */
    @Override
    public int deleteStudentUserByIds(Long[] studentUserIds)
    {
        for (Long studentUserId : studentUserIds)
        {
            StudentUser studentUser = selectStudentUserById(studentUserId);
            if (countUserStudentUserById(studentUserId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", studentUser.getUsername()));
            }
        }
        return studentUserMapper.deleteStudentUserByIds(studentUserIds);
    }

    /**
     * 新增保存学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    @Override
    public int insertStudentUser(StudentUser studentUser)
    {
        return studentUserMapper.insertStudentUser(studentUser);
    }

    /**
     * 修改保存学员信息
     *
     * @param studentUser 学员信息
     * @return 结果
     */
    @Override
    public int updateStudentUser(StudentUser studentUser)
    {
        return studentUserMapper.updateStudentUser(studentUser);
    }
}