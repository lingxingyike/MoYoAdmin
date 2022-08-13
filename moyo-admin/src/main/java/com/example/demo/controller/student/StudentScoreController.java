package com.example.demo.controller.student;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.StudentScore;
import com.example.demo.model.StudentScore;
import com.example.demo.service.IStudentScoreService;
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
@RequestMapping("/student/studentScore")
public class StudentScoreController extends BaseController
{
    @Autowired
    private IStudentScoreService studentScoreService;

    /**
     * 获取学员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(StudentScore studentScore)
    {
        startPage();
        List<StudentScore> list = studentScoreService.selectStudentScoreList(studentScore);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentScore studentScore)
    {
        List<StudentScore> list = studentScoreService.selectStudentScoreList(studentScore);
        ExcelUtil<StudentScore> util = new ExcelUtil<StudentScore>(StudentScore.class);
        util.exportExcel(response, list, "学员数据");
    }

    /**
     * 根据学员编号获取详细信息
     */
    @GetMapping(value = "/{studentScoreId}")
    public AjaxResult getInfo(@PathVariable Long studentScoreId)
    {
        return AjaxResult.success(studentScoreService.selectStudentScoreById(studentScoreId));
    }

    /**
     * 新增学员
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody StudentScore studentScore)
    {
        studentScore.setCreateBy(getUsername());
        return toAjax(studentScoreService.insertStudentScore(studentScore));
    }

    /**
     * 修改学员
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody StudentScore studentScore)
    {
        studentScore.setUpdateBy(getUsername());
        return toAjax(studentScoreService.updateStudentScore(studentScore));
    }

    /**
     * 删除学员
     */
    @Log(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentScoreIds}")
    public AjaxResult remove(@PathVariable Long[] studentScoreIds)
    {
        return toAjax(studentScoreService.deleteStudentScoreByIds(studentScoreIds));
    }

    /**
     * 获取学员选择框列表
     */
    @GetMapping("/studentScoreselect")
    public AjaxResult studentScoreselect()
    {
        List<StudentScore> studentScores = studentScoreService.selectStudentScoreAll();
        return AjaxResult.success(studentScores);
    }

    /*-------------------------------------默认方法--------------------------------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return studentScoreService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(StudentScore studentScore)
    {
        return studentScoreService.insert(studentScore);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(StudentScore studentScore)
    {
        return studentScoreService.insertSelective(studentScore);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public StudentScore selectByPrimaryKey(Long id)
    {
        return studentScoreService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(StudentScore studentScore)
    {
        return studentScoreService.updateByPrimaryKeySelective(studentScore);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(StudentScore studentScore)
    {
        return studentScoreService.updateByPrimaryKey(studentScore);
    }

}
