package cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.detectionlocalization.DetectionLocalizationDO;
import cn.iocoder.yudao.module.smartcity.service.detectionlocalization.DetectionLocalizationService;

@Tag(name = "管理后台 - 故障检测与定位")
@RestController
@RequestMapping("/smartcity/detection-localization")
@Validated
public class DetectionLocalizationController {

    @Resource
    private DetectionLocalizationService detectionLocalizationService;

    @PostMapping("/create")
    @Operation(summary = "创建故障检测与定位")
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:create')")
    public CommonResult<Long> createDetectionLocalization(@Valid @RequestBody DetectionLocalizationSaveReqVO createReqVO) {
        return success(detectionLocalizationService.createDetectionLocalization(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新故障检测与定位")
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:update')")
    public CommonResult<Boolean> updateDetectionLocalization(@Valid @RequestBody DetectionLocalizationSaveReqVO updateReqVO) {
        detectionLocalizationService.updateDetectionLocalization(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除故障检测与定位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:delete')")
    public CommonResult<Boolean> deleteDetectionLocalization(@RequestParam("id") Long id) {
        detectionLocalizationService.deleteDetectionLocalization(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得故障检测与定位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:query')")
    public CommonResult<DetectionLocalizationRespVO> getDetectionLocalization(@RequestParam("id") Long id) {
        DetectionLocalizationDO detectionLocalization = detectionLocalizationService.getDetectionLocalization(id);
        return success(BeanUtils.toBean(detectionLocalization, DetectionLocalizationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得故障检测与定位分页")
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:query')")
    public CommonResult<PageResult<DetectionLocalizationRespVO>> getDetectionLocalizationPage(@Valid DetectionLocalizationPageReqVO pageReqVO) {
        PageResult<DetectionLocalizationDO> pageResult = detectionLocalizationService.getDetectionLocalizationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DetectionLocalizationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出故障检测与定位 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:detection-localization:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDetectionLocalizationExcel(@Valid DetectionLocalizationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DetectionLocalizationDO> list = detectionLocalizationService.getDetectionLocalizationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "故障检测与定位.xls", "数据", DetectionLocalizationRespVO.class,
                        BeanUtils.toBean(list, DetectionLocalizationRespVO.class));
    }

}