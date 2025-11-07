package cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.questionclassification.QuestionClassificationDO;
import cn.iocoder.yudao.module.smartcity.service.questionclassification.QuestionClassificationService;

@Tag(name = "管理后台 - 问题录入")
@RestController
@RequestMapping("/smartcity/question-classification")
@Validated
public class QuestionClassificationController {

    @Resource
    private QuestionClassificationService questionClassificationService;

    @PostMapping("/create")
    @Operation(summary = "创建问题录入")
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:create')")
    public CommonResult<Long> createQuestionClassification(@Valid @RequestBody QuestionClassificationSaveReqVO createReqVO) {
        return success(questionClassificationService.createQuestionClassification(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题录入")
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:update')")
    public CommonResult<Boolean> updateQuestionClassification(@Valid @RequestBody QuestionClassificationSaveReqVO updateReqVO) {
        questionClassificationService.updateQuestionClassification(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:delete')")
    public CommonResult<Boolean> deleteQuestionClassification(@RequestParam("id") Long id) {
        questionClassificationService.deleteQuestionClassification(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:query')")
    public CommonResult<QuestionClassificationRespVO> getQuestionClassification(@RequestParam("id") Long id) {
        QuestionClassificationDO questionClassification = questionClassificationService.getQuestionClassification(id);
        return success(BeanUtils.toBean(questionClassification, QuestionClassificationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:query')")
    public CommonResult<PageResult<QuestionClassificationRespVO>> getQuestionClassificationPage(@Valid QuestionClassificationPageReqVO pageReqVO) {
        PageResult<QuestionClassificationDO> pageResult = questionClassificationService.getQuestionClassificationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QuestionClassificationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问题录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:question-classification:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQuestionClassificationExcel(@Valid QuestionClassificationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QuestionClassificationDO> list = questionClassificationService.getQuestionClassificationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问题录入.xls", "数据", QuestionClassificationRespVO.class,
                        BeanUtils.toBean(list, QuestionClassificationRespVO.class));
    }

}