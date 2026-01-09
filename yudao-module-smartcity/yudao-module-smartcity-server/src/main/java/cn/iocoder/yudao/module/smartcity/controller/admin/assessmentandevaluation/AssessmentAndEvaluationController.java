package cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.assessmentandevaluation.AssessmentAndEvaluationDO;
import cn.iocoder.yudao.module.smartcity.service.assessmentandevaluation.AssessmentAndEvaluationService;

@Tag(name = "管理后台 - 养护考核评价")
@RestController
@RequestMapping("/smartcity/assessment-and-evaluation")
@Validated
public class AssessmentAndEvaluationController {

    @Resource
    private AssessmentAndEvaluationService assessmentAndEvaluationService;

    @PostMapping("/create")
    @Operation(summary = "创建养护考核评价")
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:create')")
    public CommonResult<Long> createAssessmentAndEvaluation(@Valid @RequestBody AssessmentAndEvaluationSaveReqVO createReqVO) {
        return success(assessmentAndEvaluationService.createAssessmentAndEvaluation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新养护考核评价")
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:update')")
    public CommonResult<Boolean> updateAssessmentAndEvaluation(@Valid @RequestBody AssessmentAndEvaluationSaveReqVO updateReqVO) {
        assessmentAndEvaluationService.updateAssessmentAndEvaluation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除养护考核评价")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:delete')")
    public CommonResult<Boolean> deleteAssessmentAndEvaluation(@RequestParam("id") Long id) {
        assessmentAndEvaluationService.deleteAssessmentAndEvaluation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得养护考核评价")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:query')")
    public CommonResult<AssessmentAndEvaluationRespVO> getAssessmentAndEvaluation(@RequestParam("id") Long id) {
        AssessmentAndEvaluationDO assessmentAndEvaluation = assessmentAndEvaluationService.getAssessmentAndEvaluation(id);
        return success(BeanUtils.toBean(assessmentAndEvaluation, AssessmentAndEvaluationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得养护考核评价分页")
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:query')")
    public CommonResult<PageResult<AssessmentAndEvaluationRespVO>> getAssessmentAndEvaluationPage(@Valid AssessmentAndEvaluationPageReqVO pageReqVO) {
        PageResult<AssessmentAndEvaluationDO> pageResult = assessmentAndEvaluationService.getAssessmentAndEvaluationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssessmentAndEvaluationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出养护考核评价 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:assessment-and-evaluation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssessmentAndEvaluationExcel(@Valid AssessmentAndEvaluationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssessmentAndEvaluationDO> list = assessmentAndEvaluationService.getAssessmentAndEvaluationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "养护考核评价.xls", "数据", AssessmentAndEvaluationRespVO.class,
                        BeanUtils.toBean(list, AssessmentAndEvaluationRespVO.class));
    }

}