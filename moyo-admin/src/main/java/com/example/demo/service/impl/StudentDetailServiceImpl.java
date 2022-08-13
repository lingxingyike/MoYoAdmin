package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.StudentDetailMapper;
import com.example.demo.model.Question;
import com.example.demo.model.StudentDetail;
import com.example.demo.service.IStudentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class StudentDetailServiceImpl implements IStudentDetailService
{

    @Autowired
    private StudentDetailMapper studentDetailMapper;

    /**
     * 删除业务信息
     *
     * @param id 业务ID
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return studentDetailMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param studentDetail 业务信息
     * @return 结果
     */
    @Override
    public int insert(StudentDetail studentDetail)
    {
        return studentDetailMapper.insert(studentDetail);
    }

    /**
     * 插入业务信息
     *
     * @param studentDetail 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(StudentDetail studentDetail)
    {
        return studentDetailMapper.insertSelective(studentDetail);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public StudentDetail selectByPrimaryKey(Long id)
    {
        return studentDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param studentDetail 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(StudentDetail studentDetail)
    {
        return studentDetailMapper.updateByPrimaryKeySelective(studentDetail);
    }

    /**
     * 更新业务信息
     *
     * @param studentDetail 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(StudentDetail studentDetail)
    {
        return studentDetailMapper.updateByPrimaryKey(studentDetail);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param studentDetail 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<StudentDetail> selectStudentDetailList(StudentDetail studentDetail)
    {
        return studentDetailMapper.selectStudentDetailList(studentDetail);
    }

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    @Override
    public List<StudentDetail> selectStudentDetailAll()
    {
        return studentDetailMapper.selectStudentDetailAll();
    }

    /**
     * 通过题目ID查询题目信息
     *
     * @param id 题目ID
     * @return 角色对象信息
     */
    @Override
    public StudentDetail selectStudentDetailById(Long id)
    {
        return studentDetailMapper.selectStudentDetailById(id);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    @Override
    public List<Long> selectStudentDetailListById(Long id)
    {
        return studentDetailMapper.selectStudentDetailListById(id);
    }

    /**
     * 根据姓名查询列表
     *
     * @param name 姓名
     * @return 个人信息列表
     */
    @Override
    public List<StudentDetail> selectStudentDetailByStudentName(String name) {
        return studentDetailMapper.selectStudentDetailByStudentName(name);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param identity 身份证号码
     * @return 选中题目ID列表
     */
    @Override
    public StudentDetail checkStudentDetailIdentityUnique(String identity) {
        return studentDetailMapper.checkStudentDetailIdentityUnique(identity);
    }

    /**
     * 校验题目名称是否唯一
     *
     * @param studentDetail 题目信息
     * @return 结果
     */
    public String checkStudentDetailIdentityUnique(StudentDetail studentDetail)
    {
        Long id = StringUtils.isNull(studentDetail.getId()) ? -1L : studentDetail.getId();
        StudentDetail info = studentDetailMapper.checkStudentDetailIdentityUnique(studentDetail.getIdentity());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteStudentDetailById(Long id)
    {
        return studentDetailMapper.deleteStudentDetailById(id);
    }

    /**
     * 批量删除题目信息
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteStudentDetailByIds(Long[] ids)
    {
        return studentDetailMapper.deleteStudentDetailByIds(ids);
    }

    /**
     * 新增保存题目信息
     *
     * @param studentDetail 题目信息
     * @return 结果
     */
    @Override
    public int insertStudentDetail(StudentDetail studentDetail)
    {
        return studentDetailMapper.insertStudentDetail(studentDetail);
    }

    /**
     * 修改保存题目信息
     *
     * @param studentDetail 题目信息
     * @return 结果
     */
    @Override
    public int updateStudentDetail(StudentDetail studentDetail)
    {
        return studentDetailMapper.updateStudentDetail(studentDetail);
    }
    /**
     * 通过学员ID查询学员信息使用数量
     *
     * @param studentId 学员ID
     * @return 结果
     */
    @Override
    public int countStudentDetailById(Long studentId)
    {
        return studentDetailMapper.countStudentDetailById(studentId);
    }
}