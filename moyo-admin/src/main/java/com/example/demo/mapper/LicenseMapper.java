package com.example.demo.mapper;

import com.example.demo.model.License;
import com.example.demo.model.License;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LicenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(License record);

    int insertSelective(License record);

    License selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(License record);

    int updateByPrimaryKey(License record);

    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询驾驶证类型数据集合
     *
     * @param license 驾驶证类型信息
     * @return 驾驶证类型数据集合
     */
    public List<License> selectLicenseList(License license);

    /**
     * 查询所有驾驶证类型
     *
     * @return 驾驶证类型列表
     */
    public List<License> selectLicenseAll();

    /**
     * 通过驾驶证类型ID查询驾驶证类型信息
     *
     * @param licenseId 驾驶证类型ID
     * @return 角色对象信息
     */
    public License selectLicenseById(Long licenseId);

    /**
     * 根据题型ID获取驾驶证类型选择框列表
     *
     * @param userId 题型ID
     * @return 选中驾驶证类型ID列表
     */
    public List<Long> selectLicenseListById(Long userId);

    /**
     * 查询题型所属驾驶证类型组
     *
     * @param name 题型名
     * @return 结果
     */
    public List<License> selectLicensesByName(String name);

    /**
     * 删除驾驶证类型信息
     *
     * @param licenseId 驾驶证类型ID
     * @return 结果
     */
    public int deleteLicenseById(Long licenseId);

    /**
     * 批量删除驾驶证类型信息
     *
     * @param licenseIds 需要删除的驾驶证类型ID
     * @return 结果
     */
    public int deleteLicenseByIds(Long[] licenseIds);

    /**
     * 修改驾驶证类型信息
     *
     * @param license 驾驶证类型信息
     * @return 结果
     */
    public int updateLicense(License license);

    /**
     * 新增驾驶证类型信息
     *
     * @param license 驾驶证类型信息
     * @return 结果
     */
    public int insertLicense(License license);

    /**
     * 校验驾驶证类型名称
     *
     * @param licenseName 驾驶证类型名称
     * @return 结果
     */
    public License checkLicenseNameUnique(String licenseName);
}