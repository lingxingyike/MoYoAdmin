package com.example.demo.controller.exam;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.Type;
import com.example.demo.service.ITypeService;
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
@RequestMapping("/exam/type")
public class TypeController extends BaseController
{
    @Autowired
    private ITypeService typeService;

    /**
     * 获取类型列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Type type)
    {
        startPage();
        List<Type> list = typeService.selectTypeList(type);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, Type type)
    {
        List<Type> list = typeService.selectTypeList(type);
        ExcelUtil<Type> util = new ExcelUtil<Type>(Type.class);
        util.exportExcel(response, list, "类型数据");
    }

    /**
     * 根据类型编号获取详细信息
     */
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable Long typeId)
    {
        return AjaxResult.success(typeService.selectTypeById(typeId));
    }

    /**
     * 新增类型
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Type type)
    {
        if (UserConstants.NOT_UNIQUE.equals(typeService.checkTypeNameUnique(type)))
        {
            return AjaxResult.error("新增类型'" + type.getName() + "'失败，类型名称已存在");
        }
        type.setCreateBy(getUsername());
        return toAjax(typeService.insertType(type));
    }

    /**
     * 修改类型
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Type type)
    {
        if (UserConstants.NOT_UNIQUE.equals(typeService.checkTypeNameUnique(type)))
        {
            return AjaxResult.error("修改类型'" + type.getName() + "'失败，类型名称已存在");
        }
        type.setUpdateBy(getUsername());
        return toAjax(typeService.updateType(type));
    }

    /**
     * 删除类型
     */
    @Log(title = "类型管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(typeService.deleteTypeByIds(typeIds));
    }

    /**
     * 获取类型选择框列表
     */
    @GetMapping("/typeselect")
    public AjaxResult typeselect()
    {
        List<Type> types = typeService.selectTypeAll();
        return AjaxResult.success(types);
    }
    
    
    
    /*--------------默认方法----------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return typeService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(Type type)
    {
        return typeService.insert(type);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(Type type)
    {
        return typeService.insertSelective(type);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public Type selectByPrimaryKey(Long id)
    {
        return typeService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Type type)
    {
        return typeService.updateByPrimaryKeySelective(type);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(Type type)
    {
        return typeService.updateByPrimaryKey(type);
    }

}
