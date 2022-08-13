package com.example.demo.controller.exam;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.Level;
import com.example.demo.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户信息
 * 
 * @author moyo
 */
@RestController
@RequestMapping("/exam/level")
public class LevelController extends BaseController
{
    @Autowired
    private ILevelService levelService;

    /**
     * 获取科目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Level level)
    {
        startPage();
        List<Level> list = levelService.selectLevelList(level);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, Level level)
    {
        List<Level> list = levelService.selectLevelList(level);
        ExcelUtil<Level> util = new ExcelUtil<Level>(Level.class);
        util.exportExcel(response, list, "科目数据");
    }

    /**
     * 根据科目编号获取详细信息
     */
    @GetMapping(value = "/{levelId}")
    public AjaxResult getInfo(@PathVariable Long levelId)
    {
        return AjaxResult.success(levelService.selectLevelById(levelId));
    }

    /**
     * 新增科目
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Level level)
    {
        if (UserConstants.NOT_UNIQUE.equals(levelService.checkLevelNameUnique(level)))
        {
            return AjaxResult.error("新增科目'" + level.getName() + "'失败，科目名称已存在");
        }
        level.setCreateBy(getUsername());
        return toAjax(levelService.insertLevel(level));
    }

    /**
     * 修改科目
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Level level)
    {
        if (UserConstants.NOT_UNIQUE.equals(levelService.checkLevelNameUnique(level)))
        {
            return AjaxResult.error("修改科目'" + level.getName() + "'失败，科目名称已存在");
        }
        level.setUpdateBy(getUsername());
        return toAjax(levelService.updateLevel(level));
    }

    /**
     * 删除科目
     */
    @Log(title = "科目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{levelIds}")
    public AjaxResult remove(@PathVariable Long[] levelIds)
    {
        return toAjax(levelService.deleteLevelByIds(levelIds));
    }

    /**
     * 获取科目选择框列表
     */
    @GetMapping("/levelselect")
    public AjaxResult levelselect()
    {
        List<Level> levels = levelService.selectLevelAll();
        return AjaxResult.success(levels);
    }
    
    
    
    /*--------------默认方法----------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return levelService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(Level level)
    {
        return levelService.insert(level);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(Level level)
    {
        return levelService.insertSelective(level);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public Level selectByPrimaryKey(Long id)
    {
        return levelService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Level level)
    {
        return levelService.updateByPrimaryKeySelective(level);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(Level level)
    {
        return levelService.updateByPrimaryKey(level);
    }

}
