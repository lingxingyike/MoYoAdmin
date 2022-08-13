package com.example.demo.service;

import com.example.demo.model.License;
import com.example.demo.model.License;

import java.util.List;

/**
 * 业务字段 服务层
 * 
 * @author moyo
 */
public interface ILicenseService
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
     * @param license 业务字段信息
     * @return 结果
     */
    public int insert(License license);

    /**
     * 新增业务字段
     *
     * @param license 业务字段信息
     * @return 结果
     */
    public int insertSelective(License license);

    /**
     * 查询业务字段列表
     *
     * @param id 业务字段编号
     * @return 业务字段集合
     */
    public License selectByPrimaryKey(Long id);

    /**
     * 修改业务字段
     *
     * @param license 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKeySelective(License license);

    /**
     * 修改业务字段
     *
     * @param license 业务字段信息
     * @return 结果
     */
    public int updateByPrimaryKey(License license);
    
    
    /*----------------------------------新增的-----------------------------------------*/

    /**
     * 查询科目信息集合
     *
     * @param license 科目信息
     * @return 科目列表
     */
    public List<License> selectLicenseList(License license);

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    public List<License> selectLicenseAll();

    /**
     * 通过科目ID查询科目信息
     *
     * @param licenseId 科目ID
     * @return 角色对象信息
     */
    public License selectLicenseById(Long licenseId);

    /**
     * 根据题目ID获取题目选择框列表
     *
     * @param id 题目ID
     * @return 选中题目ID列表
     */
    public List<Long> selectLicenseListById(Long id);

    /**
     * 校验科目名称
     *
     * @param license 科目信息
     * @return 结果
     */
    public String checkLicenseNameUnique(License license);

    /**
     * 删除科目信息
     *
     * @param licenseId 科目ID
     * @return 结果
     */
    public int deleteLicenseById(Long licenseId);

    /**
     * 批量删除科目信息
     *
     * @param licenseIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteLicenseByIds(Long[] licenseIds);

    /**
     * 新增保存科目信息
     *
     * @param license 科目信息
     * @return 结果
     */
    public int insertLicense(License license);

    /**
     * 修改保存科目信息
     *
     * @param license 科目信息
     * @return 结果
     */
    public int updateLicense(License license);
}
