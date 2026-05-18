package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor;

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

import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.areamonitor.AreaMonitorDO;
import cn.iocoder.yudao.module.energymgmt.service.energymonitor.areamonitor.AreaMonitorService;

@Tag(name = "管理后台 - 分区能耗")
@RestController
@RequestMapping("/energymgmt/area-monitor")
@Validated
public class AreaMonitorController {

    @Resource
    private AreaMonitorService areaMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建分区能耗")
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:create')")
    public CommonResult<Long> createAreaMonitor(@Valid @RequestBody AreaMonitorSaveReqVO createReqVO) {
        return success(areaMonitorService.createAreaMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分区能耗")
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:update')")
    public CommonResult<Boolean> updateAreaMonitor(@Valid @RequestBody AreaMonitorSaveReqVO updateReqVO) {
        areaMonitorService.updateAreaMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分区能耗")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:delete')")
    public CommonResult<Boolean> deleteAreaMonitor(@RequestParam("id") Long id) {
        areaMonitorService.deleteAreaMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分区能耗")
                @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:delete')")
    public CommonResult<Boolean> deleteAreaMonitorList(@RequestParam("ids") List<Long> ids) {
        areaMonitorService.deleteAreaMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分区能耗")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:query')")
    public CommonResult<AreaMonitorRespVO> getAreaMonitor(@RequestParam("id") Long id) {
        AreaMonitorDO areaMonitor = areaMonitorService.getAreaMonitor(id);
        return success(BeanUtils.toBean(areaMonitor, AreaMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分区能耗分页")
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:query')")
    public CommonResult<PageResult<AreaMonitorRespVO>> getAreaMonitorPage(@Valid AreaMonitorPageReqVO pageReqVO) {
        PageResult<AreaMonitorDO> pageResult = areaMonitorService.getAreaMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分区能耗 Excel")
    @PreAuthorize("@ss.hasPermission('energymgmt:area-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaMonitorExcel(@Valid AreaMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaMonitorDO> list = areaMonitorService.getAreaMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分区能耗.xls", "数据", AreaMonitorRespVO.class,
                        BeanUtils.toBean(list, AreaMonitorRespVO.class));
    }

}