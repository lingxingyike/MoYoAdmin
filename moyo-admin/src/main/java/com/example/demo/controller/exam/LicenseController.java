package com.example.demo.controller.exam;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.License;
import com.example.demo.service.ILicenseService;
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
@RequestMapping("/exam/license")
public class LicenseController extends BaseController
{
    @Autowired
    private ILicenseService licenseService;

    /**
     * 获取学员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(License license)
    {
        startPage();
        List<License> list = licenseService.selectLicenseList(license);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, License license)
    {
        List<License> list = licenseService.selectLicenseList(license);
        ExcelUtil<License> util = new ExcelUtil<License>(License.class);
        util.exportExcel(response, list, "学员数据");
    }

    /**
     * 根据学员编号获取详细信息
     */
    @GetMapping(value = "/{licenseId}")
    public AjaxResult getInfo(@PathVariable Long licenseId)
    {
        return AjaxResult.success(licenseService.selectLicenseById(licenseId));
    }

    /**
     * 新增学员
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody License license)
    {
        if (UserConstants.NOT_UNIQUE.equals(licenseService.checkLicenseNameUnique(license)))
        {
            return AjaxResult.error("新增学员'" + license.getName() + "'失败，驾驶证类型已存在");
        }
        license.setCreateBy(getUsername());
        return toAjax(licenseService.insertLicense(license));
    }

    /**
     * 修改学员
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody License license)
    {
        if (UserConstants.NOT_UNIQUE.equals(licenseService.checkLicenseNameUnique(license)))
        {
            return AjaxResult.error("修改学员'" + license.getName() + "'失败，驾驶证类型已存在");
        }
        license.setUpdateBy(getUsername());
        return toAjax(licenseService.updateLicense(license));
    }

    /**
     * 删除学员
     */
    @Log(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{licenseIds}")
    public AjaxResult remove(@PathVariable Long[] licenseIds)
    {
        return toAjax(licenseService.deleteLicenseByIds(licenseIds));
    }

    /**
     * 获取学员选择框列表
     */
    @GetMapping("/licenseselect")
    public AjaxResult licenseselect()
    {
        List<License> licenses = licenseService.selectLicenseAll();
        return AjaxResult.success(licenses);
    }
    
    
    
    /*--------------默认方法----------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return licenseService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(License license)
    {
        return licenseService.insert(license);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(License license)
    {
        return licenseService.insertSelective(license);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public License selectByPrimaryKey(Long id)
    {
        return licenseService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(License license)
    {
        return licenseService.updateByPrimaryKeySelective(license);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(License license)
    {
        return licenseService.updateByPrimaryKey(license);
    }

}
