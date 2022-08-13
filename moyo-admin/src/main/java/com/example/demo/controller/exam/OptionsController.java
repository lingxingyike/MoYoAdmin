package com.example.demo.controller.exam;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.Options;
import com.example.demo.service.IOptionsService;
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
@RequestMapping("/exam/options")
public class OptionsController extends BaseController
{
    @Autowired
    private IOptionsService optionsService;

    /**
     * 获取学员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Options option)
    {
        startPage();
        List<Options> list = optionsService.selectOptionsList(option);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, Options option)
    {
        List<Options> list = optionsService.selectOptionsList(option);
        ExcelUtil<Options> util = new ExcelUtil<Options>(Options.class);
        util.exportExcel(response, list, "学员数据");
    }

    /**
     * 根据学员编号获取详细信息
     */
    @GetMapping(value = "/{optionId}")
    public AjaxResult getInfo(@PathVariable Long optionId)
    {
        return AjaxResult.success(optionsService.selectOptionsById(optionId));
    }

    /**
     * 新增学员
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Options option)
    {
        option.setCreateBy(getUsername());
        return toAjax(optionsService.insertOptions(option));
    }

    /**
     * 修改学员
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Options option)
    {
        option.setUpdateBy(getUsername());
        return toAjax(optionsService.updateOptions(option));
    }

    /**
     * 删除学员
     */
    @Log(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{optionIds}")
    public AjaxResult remove(@PathVariable Long[] optionIds)
    {
        return toAjax(optionsService.deleteOptionsByIds(optionIds));
    }

    /**
     * 获取学员选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<Options> options = optionsService.selectOptionsAll();
        return AjaxResult.success(options);
    }

    /*-------------------------------------默认方法--------------------------------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return optionsService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(Options options)
    {
        return optionsService.insert(options);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(Options options)
    {
        return optionsService.insertSelective(options);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public Options selectByPrimaryKey(Long id)
    {
        return optionsService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Options options)
    {
        return optionsService.updateByPrimaryKeySelective(options);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(Options options)
    {
        return optionsService.updateByPrimaryKey(options);
    }

}
