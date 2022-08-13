package com.example.demo.mapper;

import com.example.demo.model.Level;
import com.example.demo.model.Level;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);

    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询科目数据集合
     *
     * @param level 科目信息
     * @return 科目数据集合
     */
    public List<Level> selectLevelList(Level level);

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    public List<Level> selectLevelAll();

    /**
     * 通过科目ID查询科目信息
     *
     * @param levelId 科目ID
     * @return 角色对象信息
     */
    public Level selectLevelById(Long levelId);

    /**
     * 根据用户ID获取科目选择框列表
     *
     * @param userId 用户ID
     * @return 选中科目ID列表
     */
    public List<Long> selectLevelListById(Long userId);

    /**
     * 查询用户所属科目组
     *
     * @param username 用户名
     * @return 结果
     */
    public List<Level> selectLevelsByName(String username);

    /**
     * 删除科目信息
     *
     * @param levelId 科目ID
     * @return 结果
     */
    public int deleteLevelById(Long levelId);

    /**
     * 批量删除科目信息
     *
     * @param levelIds 需要删除的科目ID
     * @return 结果
     */
    public int deleteLevelByIds(Long[] levelIds);

    /**
     * 修改科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    public int updateLevel(Level level);

    /**
     * 新增科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    public int insertLevel(Level level);

    /**
     * 校验科目名称
     *
     * @param levelName 科目名称
     * @return 结果
     */
    public Level checkLevelNameUnique(String levelName);
}