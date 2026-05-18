package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect;

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

import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect.EnergyCollectDO;
import cn.iocoder.yudao.module.energymgmt.service.energymonitor.datacollect.EnergyCollectService;

@Tag(name = "管理后台 - 能耗采集")
@RestController
@RequestMapping("/energymgmt/energy-collect")
@Validated
public class EnergyCollectController {

    @Resource
    private EnergyCollectService energyCollectService;

    @GetMapping("/page")
    @Operation(summary = "获得能耗采集分页")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:query')")
    public CommonResult<PageResult<EnergyCollectPageRespVO>> getEnergyCollectPage(@Valid EnergyCollectPageReqVO pageReqVO) {
        PageResult<EnergyCollectDO> pageResult = energyCollectService.getEnergyCollectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnergyCollectPageRespVO.class));
    }

    @PostMapping("/dock")
    @Operation(summary = "能耗采集设备对接（新建）")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:dock')")
    public CommonResult<Boolean> dockEnergyCollect(@Valid @RequestBody EnergyCollectDockReqVO dockReqVO) {
        return success(energyCollectService.dockEnergyCollect(dockReqVO));
    }

    @PutMapping("/collect")
    @Operation(summary = "能耗采集信息采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:collect')")
    public CommonResult<Boolean> collectEnergyCollect(@Valid @RequestBody EnergyCollectCollectReqVO collectReqVO) {
        return success(energyCollectService.collectEnergyCollect(collectReqVO));
    }

    @PutMapping("/monitor")
    @Operation(summary = "能耗采集实时监测")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:monitor')")
    public CommonResult<Boolean> monitorEnergyCollect(@Valid @RequestBody EnergyCollectMonitorReqVO monitorReqVO) {
        return success(energyCollectService.monitorEnergyCollect(monitorReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出能耗采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnergyCollectExcel(@Valid EnergyCollectPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnergyCollectDO> list = energyCollectService.getEnergyCollectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "能耗采集.xls", "数据", EnergyCollectPageRespVO.class,
                BeanUtils.toBean(list, EnergyCollectPageRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得能耗采集")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:query')")
    public CommonResult<EnergyCollectPageRespVO> getEnergyCollect(@RequestParam("id") Long id) {
        EnergyCollectDO energyCollect = energyCollectService.getEnergyCollect(id);
        return success(BeanUtils.toBean(energyCollect, EnergyCollectPageRespVO.class));
    }

    @PutMapping("/check")
    @Operation(summary = "排查能耗采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:check')")
    public CommonResult<Boolean> checkEnergyCollect(@Valid @RequestBody EnergyCollectCheckReqVO checkReqVO) {
        return success(energyCollectService.checkEnergyCollect(checkReqVO));
    }

    @PutMapping("/restart")
    @Operation(summary = "重启能耗采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:restart')")
    public CommonResult<Boolean> restartEnergyCollect(@Valid @RequestBody EnergyCollectRestartReqVO restartReqVO) {
        return success(energyCollectService.restartEnergyCollect(restartReqVO));
    }

//    ———————————————————— 以上是所需接口 ————————————————————

    @PostMapping("/create")
    @Operation(summary = "创建能耗采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:create')")
    public CommonResult<Long> createEnergyCollect(@Valid @RequestBody EnergyCollectSaveReqVO createReqVO) {
        return success(energyCollectService.createEnergyCollect(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新能耗采集")
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:update')")
    public CommonResult<Boolean> updateEnergyCollect(@Valid @RequestBody EnergyCollectSaveReqVO updateReqVO) {
        energyCollectService.updateEnergyCollect(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除能耗采集")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:delete')")
    public CommonResult<Boolean> deleteEnergyCollect(@RequestParam("id") Long id) {
        energyCollectService.deleteEnergyCollect(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除能耗采集")
                @PreAuthorize("@ss.hasPermission('energymgmt:energy-collect:delete')")
    public CommonResult<Boolean> deleteEnergyCollectList(@RequestParam("ids") List<Long> ids) {
        energyCollectService.deleteEnergyCollectListByIds(ids);
        return success(true);
    }

}