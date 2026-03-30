package cn.iocoder.yudao.module.data.controller.admin.monitorinstance;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceRespVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorinstance.MonitorInstanceDO;
import cn.iocoder.yudao.module.data.service.monitorinstance.MonitorInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 监测部件实例")
@RestController
@RequestMapping("/data/monitor-instance")
@Validated
public class MonitorInstanceController {

    @Resource
    private MonitorInstanceService monitorInstanceService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件实例")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:create')")
    public CommonResult<Long> createMonitorInstance(@Valid @RequestBody MonitorInstanceSaveReqVO createReqVO) {
        return success(monitorInstanceService.createMonitorInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件实例")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:update')")
    public CommonResult<Boolean> updateMonitorInstance(@Valid @RequestBody MonitorInstanceSaveReqVO updateReqVO) {
        monitorInstanceService.updateMonitorInstance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:delete')")
    public CommonResult<Boolean> deleteMonitorInstance(@RequestParam("id") Long id) {
        monitorInstanceService.deleteMonitorInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:query')")
    public CommonResult<MonitorInstanceRespVO> getMonitorInstance(@RequestParam("id") Long id) {
        MonitorInstanceDO monitorInstance = monitorInstanceService.getMonitorInstance(id);
        return success(BeanUtils.toBean(monitorInstance, MonitorInstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件实例分页")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:query')")
    public CommonResult<PageResult<MonitorInstanceRespVO>> getMonitorInstancePage(@Valid MonitorInstancePageReqVO pageReqVO) {
        PageResult<MonitorInstanceDO> pageResult = monitorInstanceService.getMonitorInstancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitorInstanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorInstanceExcel(@Valid MonitorInstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitorInstanceDO> list = monitorInstanceService.getMonitorInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件实例.xls", "数据", MonitorInstanceRespVO.class,
                        BeanUtils.toBean(list, MonitorInstanceRespVO.class));
    }

    @PostMapping("/import-excel")
    @Operation(summary = "导入监测部件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<String> importMonitorInstanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 调用服务层进行导入
        String importResult = monitorInstanceService.importMonitorInstanceExcel(file);
        return success(importResult);
    }

    @PostMapping("/update-status-batch")
    @Operation(summary = "批量更新监测部件实例的运行状态")
    @PreAuthorize("@ss.hasPermission('data:monitor-instance:update')")
    @ApiAccessLog(operateType = UPDATE) // 记录操作日志
    public CommonResult<Integer> updateMonitorInstanceStatusBatch(@Valid @RequestBody MonitorInstanceUpdateStatusReqVO updateReqVO) {
        Integer count = monitorInstanceService.updateMonitorInstanceStatusBatch(updateReqVO);
        return success(count);
    }

}