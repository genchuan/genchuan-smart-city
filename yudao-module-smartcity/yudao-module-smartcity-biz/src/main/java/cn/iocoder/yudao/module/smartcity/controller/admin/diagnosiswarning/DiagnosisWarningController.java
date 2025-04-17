package cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.diagnosiswarning.DiagnosisWarningDO;
import cn.iocoder.yudao.module.smartcity.service.diagnosiswarning.DiagnosisWarningService;

@Tag(name = "管理后台 - 故障诊断和预警")
@RestController
@RequestMapping("/smartcity/diagnosis-warning")
@Validated
public class DiagnosisWarningController {

    @Resource
    private DiagnosisWarningService diagnosisWarningService;

    @PostMapping("/create")
    @Operation(summary = "创建故障诊断和预警")
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:create')")
    public CommonResult<Long> createDiagnosisWarning(@Valid @RequestBody DiagnosisWarningSaveReqVO createReqVO) {
        return success(diagnosisWarningService.createDiagnosisWarning(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新故障诊断和预警")
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:update')")
    public CommonResult<Boolean> updateDiagnosisWarning(@Valid @RequestBody DiagnosisWarningSaveReqVO updateReqVO) {
        diagnosisWarningService.updateDiagnosisWarning(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除故障诊断和预警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:delete')")
    public CommonResult<Boolean> deleteDiagnosisWarning(@RequestParam("id") Long id) {
        diagnosisWarningService.deleteDiagnosisWarning(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得故障诊断和预警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:query')")
    public CommonResult<DiagnosisWarningRespVO> getDiagnosisWarning(@RequestParam("id") Long id) {
        DiagnosisWarningDO diagnosisWarning = diagnosisWarningService.getDiagnosisWarning(id);
        return success(BeanUtils.toBean(diagnosisWarning, DiagnosisWarningRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得故障诊断和预警分页")
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:query')")
    public CommonResult<PageResult<DiagnosisWarningRespVO>> getDiagnosisWarningPage(@Valid DiagnosisWarningPageReqVO pageReqVO) {
        PageResult<DiagnosisWarningDO> pageResult = diagnosisWarningService.getDiagnosisWarningPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DiagnosisWarningRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出故障诊断和预警 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:diagnosis-warning:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDiagnosisWarningExcel(@Valid DiagnosisWarningPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DiagnosisWarningDO> list = diagnosisWarningService.getDiagnosisWarningPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "故障诊断和预警.xls", "数据", DiagnosisWarningRespVO.class,
                        BeanUtils.toBean(list, DiagnosisWarningRespVO.class));
    }

}