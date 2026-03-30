package cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.instrumentcalibration.InstrumentCalibrationDO;
import cn.iocoder.yudao.module.waterdetection.service.instrumentcalibration.InstrumentCalibrationService;

@Tag(name = "管理后台 - 仪器零点/量程漂移校验")
@RestController
@RequestMapping("/waterdetection/instrument-calibration")
@Validated
public class InstrumentCalibrationController {

    @Resource
    private InstrumentCalibrationService instrumentCalibrationService;

    @PostMapping("/create")
    @Operation(summary = "创建仪器零点/量程漂移校验")
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:create')")
    public CommonResult<Long> createInstrumentCalibration(@Valid @RequestBody InstrumentCalibrationSaveReqVO createReqVO) {
        return success(instrumentCalibrationService.createInstrumentCalibration(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新仪器零点/量程漂移校验")
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:update')")
    public CommonResult<Boolean> updateInstrumentCalibration(@Valid @RequestBody InstrumentCalibrationSaveReqVO updateReqVO) {
        instrumentCalibrationService.updateInstrumentCalibration(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除仪器零点/量程漂移校验")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:delete')")
    public CommonResult<Boolean> deleteInstrumentCalibration(@RequestParam("id") Long id) {
        instrumentCalibrationService.deleteInstrumentCalibration(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得仪器零点/量程漂移校验")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:query')")
    public CommonResult<InstrumentCalibrationRespVO> getInstrumentCalibration(@RequestParam("id") Long id) {
        InstrumentCalibrationDO instrumentCalibration = instrumentCalibrationService.getInstrumentCalibration(id);
        return success(BeanUtils.toBean(instrumentCalibration, InstrumentCalibrationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得仪器零点/量程漂移校验分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:query')")
    public CommonResult<PageResult<InstrumentCalibrationRespVO>> getInstrumentCalibrationPage(@Valid InstrumentCalibrationPageReqVO pageReqVO) {
        PageResult<InstrumentCalibrationDO> pageResult = instrumentCalibrationService.getInstrumentCalibrationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InstrumentCalibrationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出仪器零点/量程漂移校验 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:instrument-calibration:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstrumentCalibrationExcel(@Valid InstrumentCalibrationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstrumentCalibrationDO> list = instrumentCalibrationService.getInstrumentCalibrationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "仪器零点/量程漂移校验.xls", "数据", InstrumentCalibrationRespVO.class,
                        BeanUtils.toBean(list, InstrumentCalibrationRespVO.class));
    }

}