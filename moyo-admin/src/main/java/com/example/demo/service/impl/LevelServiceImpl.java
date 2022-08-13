package com.example.demo.service.impl;

import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.utils.StringUtils;
import com.example.demo.mapper.LevelMapper;
import com.example.demo.mapper.StudentScoreMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Level;
import com.example.demo.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 业务 服务层实现
 *
 * @author moyo
 */
@Service
public class LevelServiceImpl implements ILevelService
{

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 删除业务信息
     *
     * @param id 业务ID
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return levelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入业务信息
     *
     * @param level 业务信息
     * @return 结果
     */
    @Override
    public int insert(Level level)
    {
        return levelMapper.insert(level);
    }

    /**
     * 插入业务信息
     *
     * @param level 业务信息
     * @return 结果
     */
    @Override
    public int insertSelective(Level level)
    {
        return levelMapper.insertSelective(level);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public Level selectByPrimaryKey(Long id)
    {
        return levelMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新业务信息
     *
     * @param level 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKeySelective(Level level)
    {
        return levelMapper.updateByPrimaryKeySelective(level);
    }

    /**
     * 更新业务信息
     *
     * @param level 业务
     * @return 业务信息
     */
    @Override
    public int updateByPrimaryKey(Level level)
    {
        return levelMapper.updateByPrimaryKey(level);
    }


    /*--------------------新增的-----------------------------------*/

    /**
     * 查询科目信息集合
     *
     * @param level 科目信息
     * @return 科目信息集合
     */
    @Override
    public List<Level> selectLevelList(Level level)
    {
        return levelMapper.selectLevelList(level);
    }

    /**
     * 查询所有科目
     *
     * @return 科目列表
     */
    @Override
    public List<Level> selectLevelAll()
    {
        return levelMapper.selectLevelAll();
    }

    /**
     * 通过科目ID查询科目信息
     *
     * @param levelId 科目ID
     * @return 角色对象信息
     */
    @Override
    public Level selectLevelById(Long levelId)
    {
        return levelMapper.selectLevelById(levelId);
    }

    /**
     * 根据科目ID获取科目选择框列表
     *
     * @param id 科目ID
     * @return 选中科目ID列表
     */
    @Override
    public List<Long> selectLevelListById(Long id)
    {
        return levelMapper.selectLevelListById(id);
    }

    /**
     * 校验科目名称是否唯一
     *
     * @param level 科目信息
     * @return 结果
     */
    @Override
    public String checkLevelNameUnique(Level level)
    {
        Long levelId = StringUtils.isNull(level.getId()) ? -1L : level.getId();
        Level info = levelMapper.checkLevelNameUnique(level.getName());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != levelId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过科目ID查询科目使用数量
     *
     * @param levelId 科目ID
     * @return 结果
     */
    @Override
    public int countQuestionByLevelId(Long levelId)
    {
        return studentScoreMapper.countQuestionByLevelId(levelId)
                + questionMapper.countQuestionByLevelId(levelId);
    }

    /**
     * 删除科目信息
     *
     * @param levelId 科目ID
     * @return 结果
     */
    @Override
    public int deleteLevelById(Long levelId)
    {
        return levelMapper.deleteLevelById(levelId);
    }

    /**
     * 批量删除科目信息
     *
     * @param levelIds 需要删除的科目ID
     * @return 结果
     */
    @Override
    public int deleteLevelByIds(Long[] levelIds)
    {
        for (Long levelId : levelIds)
        {
            Level level = selectLevelById(levelId);
            if (countQuestionByLevelId(levelId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", level.getName()));
            }
        }
        return levelMapper.deleteLevelByIds(levelIds);
    }

    /**
     * 新增保存科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    @Override
    public int insertLevel(Level level)
    {
        return levelMapper.insertLevel(level);
    }

    /**
     * 修改保存科目信息
     *
     * @param level 科目信息
     * @return 结果
     */
    @Override
    public int updateLevel(Level level)
    {
        return levelMapper.updateLevel(level);
    }
}