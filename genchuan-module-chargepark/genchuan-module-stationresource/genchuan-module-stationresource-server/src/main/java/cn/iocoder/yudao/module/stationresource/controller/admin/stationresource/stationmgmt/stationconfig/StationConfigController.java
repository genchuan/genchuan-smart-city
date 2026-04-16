package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.StationConfigSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.AddStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.ops.UpdateStationConfigReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.statistics.StationConfigChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationconfig.StationConfigDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationconfig.StationConfigService;
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


@Tag(name = "管理后台 - 场站配置")
@RestController
@RequestMapping("/stationresource/station-config")
@Validated
public class StationConfigController {

    @Resource
    private StationConfigService stationConfigService;

    @GetMapping("/chart")
    @Operation(summary = "场站配置统计图表（饼图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:query')")
    public CommonResult<StationConfigChartRespVO> getStationConfigChart() {
        StationConfigChartRespVO respVO = stationConfigService.getStationConfigChart();
        return CommonResult.success(respVO);
    }
    @GetMapping("/get")
    @Operation(summary = "获得场站配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:query')")
    public CommonResult<StationConfigRespVO> getStationConfig(@RequestParam("id") Long id) {
        StationConfigDO stationConfig = stationConfigService.getStationConfig(id);
        return success(BeanUtils.toBean(stationConfig, StationConfigRespVO.class));
    }
    // ==================== 1. 批量生效 ====================
    @PutMapping("/enable")
    @Operation(summary = "批量生效场站配置")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:update')")
    public CommonResult<Boolean> enableStationConfig(@RequestBody List<Long> ids) {
        stationConfigService.enableStationConfig(ids);
        return success(true);
    }

    // ==================== 2. 批量禁用 ====================
    @PutMapping("/disable")
    @Operation(summary = "批量禁用场站配置")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:update')")
    public CommonResult<Boolean> disableStationConfig(@RequestBody List<Long> ids) {
        stationConfigService.disableStationConfig(ids);
        return success(true);
    }
    @PutMapping("/update")
    @Operation(summary = "更新场站配置")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:update')")
    public CommonResult<Boolean> updateStationConfig(@Valid @RequestBody UpdateStationConfigReqVO updateReqVO) {
        stationConfigService.updateStationConfig(updateReqVO);
        return success(true);
    }
    @PostMapping("/create")
    @Operation(summary = "创建场站配置")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:create')")
    public CommonResult<Long> createStationConfig(@Valid @RequestBody AddStationConfigReqVO createReqVO) {
        Long id = stationConfigService.addStationConfig(createReqVO);
        return success(id);
    }
    @GetMapping("/page")
    @Operation(summary = "获得场站配置分页")
    @PreAuthorize("@ss.hasPermission('stationresource:station-config:query')")
    public CommonResult<PageResult<StationConfigRespVO>> getStationConfigPage(@Valid StationConfigPageReqVO pageReqVO) {
        PageResult<StationConfigDO> pageResult = stationConfigService.getStationConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StationConfigRespVO.class));
    }



    //=========================================================================================================
//    @PostMapping("/create")
//    @Operation(summary = "创建场站配置")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-config:create')")
//    public CommonResult<Long> createStationConfig(@Valid @RequestBody StationConfigSaveReqVO createReqVO) {
//        return success(stationConfigService.createStationConfig(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新场站配置")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-config:update')")
//    public CommonResult<Boolean> updateStationConfig(@Valid @RequestBody StationConfigSaveReqVO updateReqVO) {
//        stationConfigService.updateStationConfig(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除场站配置")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('stationresource:station-config:delete')")
//    public CommonResult<Boolean> deleteStationConfig(@RequestParam("id") Long id) {
//        stationConfigService.deleteStationConfig(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除场站配置")
//                @PreAuthorize("@ss.hasPermission('stationresource:station-config:delete')")
//    public CommonResult<Boolean> deleteStationConfigList(@RequestParam("ids") List<Long> ids) {
//        stationConfigService.deleteStationConfigListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得场站配置")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-config:query')")
//    public CommonResult<StationConfigRespVO> getStationConfig(@RequestParam("id") Long id) {
//        StationConfigDO stationConfig = stationConfigService.getStationConfig(id);
//        return success(BeanUtils.toBean(stationConfig, StationConfigRespVO.class));
//    }
//

//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出场站配置 Excel")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-config:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportStationConfigExcel(@Valid StationConfigPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<StationConfigDO> list = stationConfigService.getStationConfigPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "场站配置.xls", "数据", StationConfigRespVO.class,
//                        BeanUtils.toBean(list, StationConfigRespVO.class));
//    }

}
