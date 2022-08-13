package com.example.demo.controller.student;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.StudentDetail;
import com.example.demo.model.StudentDetail;
import com.example.demo.service.IStudentDetailService;
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
@RequestMapping("/student/studentDetail")
public class StudentDetailController extends BaseController
{
    @Autowired
    private IStudentDetailService studentDetailService;


    /**
     * 获取学员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(StudentDetail option)
    {
        startPage();
        List<StudentDetail> list = studentDetailService.selectStudentDetailList(option);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentDetail option)
    {
        List<StudentDetail> list = studentDetailService.selectStudentDetailList(option);
        ExcelUtil<StudentDetail> util = new ExcelUtil<StudentDetail>(StudentDetail.class);
        util.exportExcel(response, list, "学员数据");
    }

    /**
     * 根据学员编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(studentDetailService.selectStudentDetailById(id));
    }

    /**
     * 新增学员
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody StudentDetail option)
    {
        option.setCreateBy(getUsername());
        return toAjax(studentDetailService.insertStudentDetail(option));
    }

    /**
     * 修改学员
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody StudentDetail option)
    {
        option.setUpdateBy(getUsername());
        return toAjax(studentDetailService.updateStudentDetail(option));
    }

    /**
     * 删除学员
     */
    @Log(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentDetailService.deleteStudentDetailByIds(ids));
    }

    /**
     * 获取学员选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<StudentDetail> options = studentDetailService.selectStudentDetailAll();
        return AjaxResult.success(options);
    }

    /*-------------------------------------默认方法--------------------------------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return studentDetailService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(StudentDetail studentDetail)
    {
        return studentDetailService.insert(studentDetail);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(StudentDetail studentDetail)
    {
        return studentDetailService.insertSelective(studentDetail);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public StudentDetail selectByPrimaryKey(Long id)
    {
        return studentDetailService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(StudentDetail studentDetail)
    {
        return studentDetailService.updateByPrimaryKeySelective(studentDetail);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(StudentDetail studentDetail)
    {
        return studentDetailService.updateByPrimaryKey(studentDetail);
    }

}
