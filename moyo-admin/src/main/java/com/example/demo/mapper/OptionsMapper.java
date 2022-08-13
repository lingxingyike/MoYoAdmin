package com.example.demo.mapper;

import com.example.demo.model.Options;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);


    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询学员数据集合
     *
     * @param option 学员信息
     * @return 学员数据集合
     */
    public List<Options> selectOptionsList(Options option);

    /**
     * 查询所有学员
     *
     * @return 学员列表
     */
    public List<Options> selectOptionsAll();

    /**
     * 通过学员ID查询学员信息
     *
     * @param id 学员ID
     * @return 角色对象信息
     */
    public Options selectOptionsById(Long id);

    /**
     * 根据用户ID获取学员选择框列表
     *
     * @param userId 用户ID
     * @return 选中学员ID列表
     */
    public List<Long> selectOptionsListById(Long userId);

    /**
     * 查询用户所属学员组
     *
     * @param content 选项
     * @return 结果
     */
    public List<Options> selectOptionsByContent(String content);

    /**
     * 修改学员信息
     *
     * @param option 学员信息
     * @return 结果
     */
    public int updateOptions(Options option);

    /**
     * 新增学员信息
     *
     * @param option 学员信息
     * @return 结果
     */
    public int insertOptions(Options option);

    /**
     * 删除学员信息
     *
     * @param id 学员ID
     * @return 结果
     */
    public int deleteOptionsById(Long id);

    /**
     * 批量删除学员信息
     *
     * @param ids 需要删除的学员ID
     * @return 结果
     */
    public int deleteOptionsByIds(Long[] ids);

    /**
     * 通过题型ID查询题型使用数量
     *
     * @param id 选项ID
     * @return 结果
     */
    int countOptionsByQuestionId(Long id);
    
}