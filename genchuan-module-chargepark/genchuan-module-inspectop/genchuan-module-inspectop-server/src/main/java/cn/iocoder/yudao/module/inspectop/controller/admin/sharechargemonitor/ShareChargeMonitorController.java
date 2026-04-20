package cn.iocoder.yudao.module.inspectop.controller.admin.sharechargemonitor;

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
import java.util.concurrent.ThreadLocalRandom;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.inspectop.controller.admin.sharechargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sharechargemonitor.ShareChargeMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.sharechargemonitor.ShareChargeMonitorService;

@Tag(name = "巡查巡检 - 共享充电监测")
@RestController
@RequestMapping("/inspectop/share-charge-monitor")
@Validated
public class ShareChargeMonitorController {

    @Resource
    private ShareChargeMonitorService shareChargeMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建共享充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:create')")
    public CommonResult<Long> createShareChargeMonitor(@Valid @RequestBody ShareChargeMonitorSaveReqVO createReqVO) {
        return success(shareChargeMonitorService.createShareChargeMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新共享充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:update')")
    public CommonResult<Boolean> updateShareChargeMonitor(@Valid @RequestBody ShareChargeMonitorSaveReqVO updateReqVO) {
        shareChargeMonitorService.updateShareChargeMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除共享充电监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:delete')")
    public CommonResult<Boolean> deleteShareChargeMonitor(@RequestParam("id") Long id) {
        shareChargeMonitorService.deleteShareChargeMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除共享充电监测")
                @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:delete')")
    public CommonResult<Boolean> deleteShareChargeMonitorList(@RequestParam("ids") List<Long> ids) {
        shareChargeMonitorService.deleteShareChargeMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得共享充电监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:query')")
    public CommonResult<ShareChargeMonitorRespVO> getShareChargeMonitor(@RequestParam("id") Long id) {
        ShareChargeMonitorDO shareChargeMonitor = shareChargeMonitorService.getShareChargeMonitor(id);
        return success(BeanUtils.toBean(shareChargeMonitor, ShareChargeMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得共享充电监测分页")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:query')")
    public CommonResult<PageResult<ShareChargeMonitorRespVO>> getShareChargeMonitorPage(@Valid ShareChargeMonitorPageReqVO pageReqVO) {
        PageResult<ShareChargeMonitorRespVO> pageResult = shareChargeMonitorService.getShareChargeMonitorPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/location")
    @Operation(summary = "定位")
    @Parameter(name = "id", description = "监测记录ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:location')")
    public CommonResult<ShareChargeMonitorLocationRespVO> getShareChargeMonitorLocation(@RequestParam("id") Long id) {
        // 1. 从Service层获取基础定位信息（包含longitude、latitude、stationName）
        ShareChargeMonitorLocationRespVO locationRespVO = shareChargeMonitorService.getShareChargeMonitorLocation(id);

        // 2. 模拟生成设备编号：SC-01 到 SC-50
        int deviceNum = ThreadLocalRandom.current().nextInt(1, 51); // 生成1-50的随机数
        String deviceCode = String.format("SC-%02d", deviceNum); // 格式化为两位数字，如SC-01

        // 3. 设置设备编号到响应对象
        locationRespVO.setDeviceCode(deviceCode);

        // 4. 返回成功响应
        return success(locationRespVO);
    }

    @PutMapping("/alarm")
    @Operation(summary = "告警更新共享充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:alarm')")
    public CommonResult<Boolean> alarmShareChargeMonitor(@Valid @RequestBody ShareChargeMonitorAlarmReqVO alarmReqVO) {
        // 调用Service层处理告警更新
        shareChargeMonitorService.alarmShareChargeMonitor(alarmReqVO);

        // 返回成功响应
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:chart')")
    public CommonResult<ShareChargeMonitorChartRespVO> getShareChargeMonitorChart(@Valid ShareChargeMonitorChartReqVO reqVO) {
        // 调用Service层获取图表数据
        ShareChargeMonitorChartRespVO chartData = shareChargeMonitorService.getShareChargeMonitorChart(reqVO);

        // 返回成功响应
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出共享充电监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:share-charge-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportShareChargeMonitorExcel(@Valid ShareChargeMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ShareChargeMonitorRespVO> list = shareChargeMonitorService.getShareChargeMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "共享充电监测.xls", "数据", ShareChargeMonitorRespVO.class,
                        BeanUtils.toBean(list, ShareChargeMonitorRespVO.class));
    }

}