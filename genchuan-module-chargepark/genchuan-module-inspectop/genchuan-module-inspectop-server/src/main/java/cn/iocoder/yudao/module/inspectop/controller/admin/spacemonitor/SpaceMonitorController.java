package cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.spacemonitor.SpaceMonitorService;

@Tag(name = "管理后台 - 车位状态监测")
@RestController
@RequestMapping("/inspectop/space-monitor")
@Validated
public class SpaceMonitorController {

    @Resource
    private SpaceMonitorService spaceMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建车位状态监测")
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:create')")
    public CommonResult<Long> createSpaceMonitor(@Valid @RequestBody SpaceMonitorSaveReqVO createReqVO) {
        return success(spaceMonitorService.createSpaceMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车位状态监测")
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:update')")
    public CommonResult<Boolean> updateSpaceMonitor(@Valid @RequestBody SpaceMonitorSaveReqVO updateReqVO) {
        spaceMonitorService.updateSpaceMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车位状态监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:delete')")
    public CommonResult<Boolean> deleteSpaceMonitor(@RequestParam("id") Long id) {
        spaceMonitorService.deleteSpaceMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除车位状态监测")
                @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:delete')")
    public CommonResult<Boolean> deleteSpaceMonitorList(@RequestParam("ids") List<Long> ids) {
        spaceMonitorService.deleteSpaceMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车位状态监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:query')")
    public CommonResult<SpaceMonitorRespVO> getSpaceMonitor(@RequestParam("id") Long id) {
        SpaceMonitorDO spaceMonitor = spaceMonitorService.getSpaceMonitor(id);
        return success(BeanUtils.toBean(spaceMonitor, SpaceMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位状态监测分页")
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:query')")
    public CommonResult<List<SpaceMonitorRespVO>> getSpaceMonitorPage(@Valid SpaceMonitorPageReqVO pageReqVO) {
        // 直接返回Service的结果，Service的结果已经是VO
        List<SpaceMonitorRespVO> pageResult = spaceMonitorService.getSpaceMonitorPage(pageReqVO);
        return success(pageResult); // 移除了 BeanUtils.toBean 转换
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车位状态监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:space-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSpaceMonitorExcel(@Valid SpaceMonitorPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // Service返回的就是List<SpaceMonitorRespVO>
        List<SpaceMonitorRespVO> list = spaceMonitorService.getSpaceMonitorPage(pageReqVO);
        // 导出 Excel，list现在直接就是VO对象
        ExcelUtils.write(response, "车位状态监测.xls", "数据", SpaceMonitorRespVO.class, list); // 移除了 BeanUtils.toBean 转换
    }

}