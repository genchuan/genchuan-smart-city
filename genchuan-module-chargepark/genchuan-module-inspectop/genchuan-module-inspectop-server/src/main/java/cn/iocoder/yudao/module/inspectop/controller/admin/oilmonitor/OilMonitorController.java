package cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.oilmonitor.OilMonitorService;

@Tag(name = "管理后台 - 油车占位监测")
@RestController
@RequestMapping("/inspectop/oil-monitor")
@Validated
public class OilMonitorController {

    @Resource
    private OilMonitorService oilMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建油车占位监测")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:create')")
    public CommonResult<Long> createOilMonitor(@Valid @RequestBody OilMonitorSaveReqVO createReqVO) {
        return success(oilMonitorService.createOilMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新油车占位监测")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:update')")
    public CommonResult<Boolean> updateOilMonitor(@Valid @RequestBody OilMonitorSaveReqVO updateReqVO) {
        oilMonitorService.updateOilMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除油车占位监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:delete')")
    public CommonResult<Boolean> deleteOilMonitor(@RequestParam("id") Long id) {
        oilMonitorService.deleteOilMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除油车占位监测")
                @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:delete')")
    public CommonResult<Boolean> deleteOilMonitorList(@RequestParam("ids") List<Long> ids) {
        oilMonitorService.deleteOilMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得油车占位监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:query')")
    public CommonResult<OilMonitorRespVO> getOilMonitor(@RequestParam("id") Long id) {
        OilMonitorDO oilMonitor = oilMonitorService.getOilMonitor(id);
        return success(BeanUtils.toBean(oilMonitor, OilMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得油车占位监测分页")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:query')")
    public CommonResult<PageResult<OilMonitorRespVO>> getOilMonitorPage(@Valid OilMonitorPageReqVO pageReqVO) {
        PageResult<OilMonitorDO> pageResult = oilMonitorService.getOilMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出油车占位监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOilMonitorExcel(@Valid OilMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OilMonitorDO> list = oilMonitorService.getOilMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "油车占位监测.xls", "数据", OilMonitorRespVO.class,
                        BeanUtils.toBean(list, OilMonitorRespVO.class));
    }

}