/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradeSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentGradeDO;
import cn.iocoder.yudao.module.envirhealth.service.user.assessmentgrade.AssessmentGradeService;

@Tag(name = "管理后台 - 考核等级字典表")
@RestController
@RequestMapping("/envirhealth/assessment-grade")
@Validated
public class AssessmentGradeController {

    @Resource
    private AssessmentGradeService assessmentGradeService;

    @PostMapping("/create")
    @Operation(summary = "创建考核等级字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:create')")
    public CommonResult<Long> createAssessmentGrade(@Valid @RequestBody AssessmentGradeSaveReqVO createReqVO) {
        return success(assessmentGradeService.createAssessmentGrade(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考核等级字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:update')")
    public CommonResult<Boolean> updateAssessmentGrade(@Valid @RequestBody AssessmentGradeSaveReqVO updateReqVO) {
        assessmentGradeService.updateAssessmentGrade(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考核等级字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:delete')")
    public CommonResult<Boolean> deleteAssessmentGrade(@RequestParam("id") Long id) {
        assessmentGradeService.deleteAssessmentGrade(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考核等级字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:query')")
    public CommonResult<AssessmentGradeRespVO> getAssessmentGrade(@RequestParam("id") Long id) {
        AssessmentGradeDO assessmentGrade = assessmentGradeService.getAssessmentGrade(id);
        return success(BeanUtils.toBean(assessmentGrade, AssessmentGradeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考核等级字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:query')")
    public CommonResult<PageResult<AssessmentGradeRespVO>> getAssessmentGradePage(@Valid AssessmentGradePageReqVO pageReqVO) {
        PageResult<AssessmentGradeDO> pageResult = assessmentGradeService.getAssessmentGradePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssessmentGradeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考核等级字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:assessment-grade:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssessmentGradeExcel(@Valid AssessmentGradePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssessmentGradeDO> list = assessmentGradeService.getAssessmentGradePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考核等级字典表.xls", "数据", AssessmentGradeRespVO.class,
                        BeanUtils.toBean(list, AssessmentGradeRespVO.class));
    }

}*/
