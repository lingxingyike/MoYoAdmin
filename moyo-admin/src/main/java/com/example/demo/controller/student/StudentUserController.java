package com.example.demo.controller.student;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.StudentUser;
import com.example.demo.service.IStudentUserService;
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
@RequestMapping("/student/studentUser")
public class StudentUserController extends BaseController
{
    @Autowired
    private IStudentUserService studentUserService;

    /**
     * 获取学员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(StudentUser studentUser)
    {
        startPage();
        List<StudentUser> list = studentUserService.selectStudentUserList(studentUser);
        return getDataTable(list);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentUser studentUser)
    {
        List<StudentUser> list = studentUserService.selectStudentUserList(studentUser);
        ExcelUtil<StudentUser> util = new ExcelUtil<StudentUser>(StudentUser.class);
        util.exportExcel(response, list, "学员数据");
    }

    /**
     * 根据学员编号获取详细信息
     */
    @GetMapping(value = "/{studentUserId}")
    public AjaxResult getInfo(@PathVariable Long studentUserId)
    {
        return AjaxResult.success(studentUserService.selectStudentUserById(studentUserId));
    }

    /**
     * 新增学员
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody StudentUser studentUser)
    {
        if (UserConstants.NOT_UNIQUE.equals(studentUserService.checkStudentUserNameUnique(studentUser)))
        {
            return AjaxResult.error("新增学员'" + studentUser.getUsername() + "'失败，学员名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(studentUserService.checkStudentUserNoUnique(studentUser)))
        {
            return AjaxResult.error("新增学员'" + studentUser.getUsername() + "'失败，学员编码已存在");
        }
        studentUser.setCreateBy(getUsername());
        Integer row = studentUserService.insertStudentUser(studentUser);
        return row > 0 ? AjaxResult.success(studentUser) : AjaxResult.error();
        //return toAjax(studentUserService.insertStudentUser(studentUser));
    }

    /**
     * 修改学员
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody StudentUser studentUser)
    {
        if (UserConstants.NOT_UNIQUE.equals(studentUserService.checkStudentUserNameUnique(studentUser)))
        {
            return AjaxResult.error("修改学员'" + studentUser.getUsername() + "'失败，学员名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(studentUserService.checkStudentUserNoUnique(studentUser)))
        {
            return AjaxResult.error("修改学员'" + studentUser.getUsername() + "'失败，学员编码已存在");
        }
        studentUser.setUpdateBy(getUsername());
        return toAjax(studentUserService.updateStudentUser(studentUser));
    }

    /**
     * 删除学员
     */
    @Log(title = "学员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentUserIds}")
    public AjaxResult remove(@PathVariable Long[] studentUserIds)
    {
        return toAjax(studentUserService.deleteStudentUserByIds(studentUserIds));
    }

    /**
     * 获取学员选择框列表
     */
    @GetMapping("/studentUserselect")
    public AjaxResult studentUserselect()
    {
        List<StudentUser> studentUsers = studentUserService.selectStudentUserAll();
        return AjaxResult.success(studentUsers);
    }
    
    
    
    /*--------------默认方法----------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return studentUserService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(StudentUser studentUser)
    {
        return studentUserService.insert(studentUser);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(StudentUser studentUser)
    {
        return studentUserService.insertSelective(studentUser);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public StudentUser selectByPrimaryKey(Long id)
    {
        return studentUserService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(StudentUser studentUser)
    {
        return studentUserService.updateByPrimaryKeySelective(studentUser);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(StudentUser studentUser)
    {
        return studentUserService.updateByPrimaryKey(studentUser);
    }

}
