package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor.vo.*;
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

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.oilmonitor.OilMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.devicemonitor.oilmonitor.OilMonitorService;

@Tag(name = "巡查巡检 - 油车占位监测")
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
        // 直接返回Service的结果，Service的结果已经是VO
        PageResult<OilMonitorRespVO> pageResult = oilMonitorService.getOilMonitorPage(pageReqVO);
        return success(pageResult); // 移除了 BeanUtils.toBean 转换
    }

    @PutMapping("/batch-process")
    @Operation(summary = "批量处置")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:batch-process')")
    public CommonResult<Boolean> batchProcessOilMonitor(@Valid @RequestBody OilMonitorBatchProcessReqVO batchProcessReqVO) {
        oilMonitorService.batchProcessOilMonitor(batchProcessReqVO);
        return success(true);
    }

    @PutMapping("/process")
    @Operation(summary = "处置")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:process')")
    public CommonResult<Boolean> processOilMonitor(@Valid @RequestBody OilMonitorBatchProcessReqVO batchProcessReqVO) {
        oilMonitorService.batchProcessOilMonitor(batchProcessReqVO);
        return success(true);
    }

    @PutMapping("/update-process")
    @Operation(summary = "更新进度")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:update-process')")
    public CommonResult<Boolean> UpdateProcessOilMonitor(@Valid @RequestBody OilMonitorBatchProcessReqVO batchProcessReqVO) {
        oilMonitorService.batchProcessOilMonitor(batchProcessReqVO);
        return success(true);
    }

    @PutMapping("/ignore")
    @Operation(summary = "忽略油车占位监测")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:ignore')")
    public CommonResult<Boolean> ignoreOilMonitor(@Valid @RequestBody OilMonitorIgnoreReqVO ignoreReqVO) {
        oilMonitorService.ignoreOilMonitor(ignoreReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取油车占位监控数据")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:chart')")
    public CommonResult<OilMonitorChartRespVO> getOilMonitorChart(@Valid OilMonitorChartReqVO reqVO) {
        OilMonitorChartRespVO chartData = oilMonitorService.getOilMonitorChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出油车占位监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:oil-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOilMonitorExcel(@Valid OilMonitorPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

        // Service返回的就是List<OilMonitorRespVO>
        List<OilMonitorRespVO> voList = oilMonitorService.getOilMonitorPage(pageReqVO).getList();

        // 【新增】对VO列表中的字典值进行转换（数字 -> 中文）
        convertOilMonitorDictValues(voList);

        // 导出 Excel
        ExcelUtils.write(response, "油车占位监测.xls", "数据", OilMonitorRespVO.class, voList);
    }

    /**
     * 【新增】转换油车占位监测字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 processStatus 字段。
     * @param voList 油车占位监测响应VO列表
     */
    private void convertOilMonitorDictValues(List<OilMonitorRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (OilMonitorRespVO vo : voList) {
            // 转换处置状态
            vo.setProcessStatus(convertProcessStatus(vo.getProcessStatus()));
        }
    }

    /**
     * 【新增】转换油车占位监测处置状态字典值
     * 根据您提供的映射：0-已处理, 1-未处理, 2-处理中
     * @param statusCode 状态编码（例如 "0", "1", "2"）
     * @return 对应的中文状态描述
     */
    private String convertProcessStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }

        // 使用trim()去除可能的空格
        String code = statusCode.trim();

        switch (code) {
            case "0":
                return "已处理";
            case "1":
                return "未处理";
            case "2":
                return "处理中";
            default:
                // 如果遇到未知编码，返回原编码以便排查问题
                return statusCode;
        }
    }

}