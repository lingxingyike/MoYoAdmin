package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.LicenseMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.StudentScoreMapper;
import com.example.demo.model.License;
import com.example.demo.service.ILicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 * 
 * @author moyo
 */
@Service
public class LicenseServiceImpl implements ILicenseService
{

    @Autowired
    private LicenseMapper licenseMapper;

    @Autowired
    private QuestionMapper questionMapper;

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
        return licenseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param license 业务信息
     * @return 结果
     */
    @Override
    public int insert(License license)
    {
        return licenseMapper.insert(license);
    }

    /**
     * 插入业务信息
     *
     * @param license 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(License license)
    {
        return licenseMapper.insertSelective(license);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public License selectByPrimaryKey(Long id)
    {
        return licenseMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param license 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(License license)
    {
        return licenseMapper.updateByPrimaryKeySelective(license);
    }

    /**
     * 更新业务信息
     *
     * @param license 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(License license)
    {
        return licenseMapper.updateByPrimaryKey(license);
    }



    /*--------------------新增的-----------------------------------*/

    /**
     * 查询题目信息集合
     *
     * @param license 题目信息
     * @return 题目信息集合
     */
    @Override
    public List<License> selectLicenseList(License license)
    {
        return licenseMapper.selectLicenseList(license);
    }

    /**
     * 查询所有题目
     *
     * @return 题目列表
     */
    @Override
    public List<License> selectLicenseAll()
    {
        return licenseMapper.selectLicenseAll();
    }

    /**
     * 通过题目ID查询题目信息
     *
     * @param licenseId 题目ID
     * @return 角色对象信息
     */
    @Override
    public License selectLicenseById(Long licenseId)
    {
        return licenseMapper.selectLicenseById(licenseId);
    }

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    @Override
    public List<Long> selectLicenseListById(Long id)
    {
        return licenseMapper.selectLicenseListById(id);
    }

    /**
     * 校验题目名称是否唯一
     *
     * @param license 题目信息
     * @return 结果
     */
    @Override
    public String checkLicenseNameUnique(License license)
    {
        Long licenseId = StringUtils.isNull(license.getId()) ? -1L : license.getId();
        License info = licenseMapper.checkLicenseNameUnique(license.getName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != licenseId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过题目ID查询题目使用数量
     *
     * @param licenseId 题目ID
     * @return 结果
     */
    public int countQuestionByLicenseId(Long licenseId)
    {
        return studentScoreMapper.countQuestionByLicenseId(licenseId)
                + questionMapper.countQuestionByLicenseId(licenseId);
    }

    /**
     * 删除题目信息
     *
     * @param licenseId 题目ID
     * @return 结果
     */
    @Override
    public int deleteLicenseById(Long licenseId)
    {
        return licenseMapper.deleteLicenseById(licenseId);
    }

    /**
     * 批量删除题目信息
     *
     * @param licenseIds 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteLicenseByIds(Long[] licenseIds)
    {
        for (Long licenseId : licenseIds)
        {
            License license = selectLicenseById(licenseId);
            if (countQuestionByLicenseId(licenseId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", license.getName()));
            }
        }
        return licenseMapper.deleteLicenseByIds(licenseIds);
    }

    /**
     * 新增保存题目信息
     *
     * @param license 题目信息
     * @return 结果
     */
    @Override
    public int insertLicense(License license)
    {
        return licenseMapper.insertLicense(license);
    }

    /**
     * 修改保存题目信息
     *
     * @param license 题目信息
     * @return 结果
     */
    @Override
    public int updateLicense(License license)
    {
        return licenseMapper.updateLicense(license);
    }
}