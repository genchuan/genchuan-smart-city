package cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.metercalibration.MeterCalibrationDO;
import cn.iocoder.yudao.module.waterdetection.service.metercalibration.MeterCalibrationService;

@Tag(name = "管理后台 - 监测仪表校准管理")
@RestController
@RequestMapping("/waterdetection/meter-calibration")
@Validated
public class MeterCalibrationController {

    @Resource
    private MeterCalibrationService meterCalibrationService;

    @PostMapping("/create")
    @Operation(summary = "创建监测仪表校准管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:create')")
    public CommonResult<Long> createMeterCalibration(@Valid @RequestBody MeterCalibrationSaveReqVO createReqVO) {
        return success(meterCalibrationService.createMeterCalibration(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测仪表校准管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:update')")
    public CommonResult<Boolean> updateMeterCalibration(@Valid @RequestBody MeterCalibrationSaveReqVO updateReqVO) {
        meterCalibrationService.updateMeterCalibration(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测仪表校准管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:delete')")
    public CommonResult<Boolean> deleteMeterCalibration(@RequestParam("id") Long id) {
        meterCalibrationService.deleteMeterCalibration(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测仪表校准管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:query')")
    public CommonResult<MeterCalibrationRespVO> getMeterCalibration(@RequestParam("id") Long id) {
        MeterCalibrationDO meterCalibration = meterCalibrationService.getMeterCalibration(id);
        return success(BeanUtils.toBean(meterCalibration, MeterCalibrationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测仪表校准管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:query')")
    public CommonResult<PageResult<MeterCalibrationRespVO>> getMeterCalibrationPage(@Valid MeterCalibrationPageReqVO pageReqVO) {
        PageResult<MeterCalibrationDO> pageResult = meterCalibrationService.getMeterCalibrationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MeterCalibrationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测仪表校准管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:meter-calibration:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMeterCalibrationExcel(@Valid MeterCalibrationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MeterCalibrationDO> list = meterCalibrationService.getMeterCalibrationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测仪表校准管理.xls", "数据", MeterCalibrationRespVO.class,
                        BeanUtils.toBean(list, MeterCalibrationRespVO.class));
    }

}