package com.example.demo.controller.exam;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.constant.UserConstants;
import com.example.demo.common.core.controller.BaseController;
import com.example.demo.common.core.domain.AjaxResult;
import com.example.demo.common.core.page.TableDataInfo;
import com.example.demo.common.enums.BusinessType;
import com.example.demo.common.utils.poi.ExcelUtil;
import com.example.demo.model.Question;
import com.example.demo.model.ms.QuestionOptions;
import com.example.demo.service.IQuestionService;
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
@RequestMapping("/exam/question")
public class QuestionController extends BaseController
{
    @Autowired
    private IQuestionService questionService;

    /**
     * 获取题目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Question question)
    {
        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

//    /**
//     * 获取题目列表
//     */
//    @GetMapping("/list")
//    public TableDataInfo list(Question question)
//    {
//        startPage();
//        List<QuestionOptions> list = questionService.selectQuestionOptionsList(question);
//        return getDataTable(list);
//    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, Question question)
    {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        util.exportExcel(response, list, "题目数据");
    }

    /**
     * 根据题目编号获取详细信息
     */
    @GetMapping(value = "/{questionId}")
    public AjaxResult getInfo(@PathVariable Long questionId)
    {
        return AjaxResult.success(questionService.selectQuestionById(questionId));
    }

    /**
     * 新增题目
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Question question)
    {
        if (UserConstants.NOT_UNIQUE.equals(questionService.checkQuestionTopicUnique(question)))
        {
            return AjaxResult.error("新增题目'" + question.getTopic() + "'失败，题目已存在");
        }
        question.setCreateBy(getUsername());
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改题目
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Question question)
    {
        if (UserConstants.NOT_UNIQUE.equals(questionService.checkQuestionTopicUnique(question)))
        {
            return AjaxResult.error("修改题目'" + question.getTopic() + "'失败，题目已存在");
        }
        question.setUpdateBy(getUsername());
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除题目
     */
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{questionIds}")
    public AjaxResult remove(@PathVariable Long[] questionIds)
    {
        return toAjax(questionService.deleteQuestionByIds(questionIds));
    }

    /**
     * 获取题目选择框列表
     */
    @GetMapping("/questionselect")
    public AjaxResult questionselect()
    {
        List<Question> questions = questionService.selectQuestionAll();
        return AjaxResult.success(questions);
    }

    /*-------------------------------------默认方法--------------------------------------*/
    /**
     * 删除数据
     */
    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long id)
    {
        return questionService.deleteByPrimaryKey(id);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insert")
    public int insert(Question question)
    {
        return questionService.insert(question);
    }
    /**
     * 新增数据
     */
    @PostMapping("/insertSelective")
    public int insertSelective(Question question)
    {
        return questionService.insertSelective(question);
    }
    /**
     * 查询数据
     */
    @GetMapping("/selectByPrimaryKey")
    public Question selectByPrimaryKey(Long id)
    {
        return questionService.selectByPrimaryKey(id);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Question question)
    {
        return questionService.updateByPrimaryKeySelective(question);
    }
    /**
     * 更新数据
     */
    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(Question question)
    {
        return questionService.updateByPrimaryKey(question);
    }

}
