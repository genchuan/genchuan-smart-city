package cn.iocoder.yudao.module.envirhealth.controller.admin.river;

import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.river.monitorstatus.MonitorStatusService;

@Tag(name = "字典表 - 监测状态")
@RestController
@RequestMapping("/envirhealth/monitor-status")
@Validated
public class MonitorStatusController {

    @Resource
    private MonitorStatusService monitorStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建监测状态字典表】")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:create')")
    public CommonResult<Long> createMonitorStatus(@Valid @RequestBody MonitorStatusSaveReqVO createReqVO) {
        return success(monitorStatusService.createMonitorStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:update')")
    public CommonResult<Boolean> updateMonitorStatus(@Valid @RequestBody MonitorStatusSaveReqVO updateReqVO) {
        monitorStatusService.updateMonitorStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:delete')")
    public CommonResult<Boolean> deleteMonitorStatus(@RequestParam("id") Long id) {
        monitorStatusService.deleteMonitorStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:query')")
    public CommonResult<MonitorStatusRespVO> getMonitorStatus(@RequestParam("id") Long id) {
        MonitorStatusDO monitorStatus = monitorStatusService.getMonitorStatus(id);
        return success(BeanUtils.toBean(monitorStatus, MonitorStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:query')")
    public CommonResult<PageResult<MonitorStatusRespVO>> getMonitorStatusPage(@Valid MonitorStatusPageReqVO pageReqVO) {
        PageResult<MonitorStatusDO> pageResult = monitorStatusService.getMonitorStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitorStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorStatusExcel(@Valid MonitorStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitorStatusDO> list = monitorStatusService.getMonitorStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测状态字典表.xls", "数据", MonitorStatusRespVO.class,
                        BeanUtils.toBean(list, MonitorStatusRespVO.class));
    }

    /**
     * 获得监测状态字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得监测状态(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:monitor-status:query')")
    public CommonResult<List<OptionVO>> getMonitorStatusOptions() {
        return success(monitorStatusService.getMonitorStatusOptions());
    }
}
