package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDeviceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDeviceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisdevice.AnalysisDeviceDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysisdevice.AnalysisDeviceService;

@Tag(name = "管理后台 - 按设备分域分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-device")
@Validated
public class AnalysisDeviceController {

    @Resource
    private AnalysisDeviceService analysisDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建按设备分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:create')")
    public CommonResult<Long> createAnalysisDevice(@Valid @RequestBody AnalysisDeviceSaveReqVO createReqVO) {
        return success(analysisDeviceService.createAnalysisDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按设备分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:update')")
    public CommonResult<Boolean> updateAnalysisDevice(@Valid @RequestBody AnalysisDeviceSaveReqVO updateReqVO) {
        analysisDeviceService.updateAnalysisDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按设备分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:delete')")
    public CommonResult<Boolean> deleteAnalysisDevice(@RequestParam("id") Long id) {
        analysisDeviceService.deleteAnalysisDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按设备分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:query')")
    public CommonResult<AnalysisDeviceRespVO> getAnalysisDevice(@RequestParam("id") Long id) {
        AnalysisDeviceDO analysisDevice = analysisDeviceService.getAnalysisDevice(id);
        return success(BeanUtils.toBean(analysisDevice, AnalysisDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按设备分域分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:query')")
    public CommonResult<PageResult<AnalysisDeviceRespVO>> getAnalysisDevicePage(@Valid AnalysisDevicePageReqVO pageReqVO) {
        PageResult<AnalysisDeviceDO> pageResult = analysisDeviceService.getAnalysisDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按设备分域分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisDeviceExcel(@Valid AnalysisDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisDeviceDO> list = analysisDeviceService.getAnalysisDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按设备分域分析研判统计.xls", "数据", AnalysisDeviceRespVO.class,
                        BeanUtils.toBean(list, AnalysisDeviceRespVO.class));
    }

}