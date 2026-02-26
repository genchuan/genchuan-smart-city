package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo.QuestionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.question.QuestionDO;
import cn.iocoder.yudao.module.evaluate.service.question.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 题目")
@RestController
@RequestMapping("/evaluate/question")
@Validated
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("/create")
    @Operation(summary = "创建题目")
    @PreAuthorize("@ss.hasPermission('evaluate:question:create')")
    public CommonResult<Long> createQuestion(@Valid @RequestBody QuestionSaveReqVO createReqVO) {
        return success(questionService.createQuestion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新题目")
    @PreAuthorize("@ss.hasPermission('evaluate:question:update')")
    public CommonResult<Boolean> updateQuestion(@Valid @RequestBody QuestionSaveReqVO updateReqVO) {
        questionService.updateQuestion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:question:delete')")
    public CommonResult<Boolean> deleteQuestion(@RequestParam("id") Long id) {
        questionService.deleteQuestion(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除题目")
                @PreAuthorize("@ss.hasPermission('evaluate:question:delete')")
    public CommonResult<Boolean> deleteQuestionList(@RequestParam("ids") List<Long> ids) {
        questionService.deleteQuestionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得题目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:question:query')")
    public CommonResult<QuestionRespVO> getQuestion(@RequestParam("id") Long id) {
        QuestionDO question = questionService.getQuestion(id);
        return success(BeanUtils.toBean(question, QuestionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得题目分页")
    @PreAuthorize("@ss.hasPermission('evaluate:question:query')")
    public CommonResult<PageResult<QuestionRespVO>> getQuestionPage(@Valid QuestionPageReqVO pageReqVO) {
        PageResult<QuestionDO> pageResult = questionService.getQuestionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QuestionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出题目 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:question:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQuestionExcel(@Valid QuestionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QuestionDO> list = questionService.getQuestionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "题目.xls", "数据", QuestionRespVO.class,
                        BeanUtils.toBean(list, QuestionRespVO.class));
    }

}