package com.example.demo.mapper;

import com.example.demo.model.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    /*---------------------------------------新增的----------------------------------------*/
    /**
     * 查询题型数据集合
     *
     * @param type 题型信息
     * @return 题型数据集合
     */
    public List<Type> selectTypeList(Type type);

    /**
     * 查询所有题型
     *
     * @return 题型列表
     */
    public List<Type> selectTypeAll();

    /**
     * 通过题型ID查询题型信息
     *
     * @param typeId 题型ID
     * @return 角色对象信息
     */
    public Type selectTypeById(Long typeId);

    /**
     * 根据用户ID获取题型选择框列表
     *
     * @param typeId 用户ID
     * @return 选中题型ID列表
     */
    public List<Long> selectTypeListById(Long typeId);

    /**
     * 查询题目所属题型组
     *
     * @param username 用户名
     * @return 结果
     */
    public List<Type> selectTypesByTypeName(String username);

    /**
     * 删除题型信息
     *
     * @param typeId 题型ID
     * @return 结果
     */
    public int deleteTypeById(Long typeId);

    /**
     * 批量删除题型信息
     *
     * @param typeIds 需要删除的题型ID
     * @return 结果
     */
    public int deleteTypeByIds(Long[] typeIds);

    /**
     * 修改题型信息
     *
     * @param type 题型信息
     * @return 结果
     */
    public int updateType(Type type);

    /**
     * 新增题型信息
     *
     * @param type 题型信息
     * @return 结果
     */
    public int insertType(Type type);

    /**
     * 校验题型名称
     *
     * @param typeName 题型名称
     * @return 结果
     */
    public Type checkTypeNameUnique(String typeName);
}