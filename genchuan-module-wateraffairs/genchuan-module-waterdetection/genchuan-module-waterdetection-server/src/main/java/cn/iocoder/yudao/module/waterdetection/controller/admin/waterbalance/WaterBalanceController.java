package cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterbalance.WaterBalanceDO;
import cn.iocoder.yudao.module.waterdetection.service.waterbalance.WaterBalanceService;

@Tag(name = "管理后台 - 水量平衡与漏损分析")
@RestController
@RequestMapping("/waterdetection/water-balance")
@Validated
public class WaterBalanceController {

    @Resource
    private WaterBalanceService waterBalanceService;

    @PostMapping("/create")
    @Operation(summary = "创建水量平衡与漏损分析")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:create')")
    public CommonResult<Long> createWaterBalance(@Valid @RequestBody WaterBalanceSaveReqVO createReqVO) {
        return success(waterBalanceService.createWaterBalance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新水量平衡与漏损分析")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:update')")
    public CommonResult<Boolean> updateWaterBalance(@Valid @RequestBody WaterBalanceSaveReqVO updateReqVO) {
        waterBalanceService.updateWaterBalance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除水量平衡与漏损分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:delete')")
    public CommonResult<Boolean> deleteWaterBalance(@RequestParam("id") Long id) {
        waterBalanceService.deleteWaterBalance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得水量平衡与漏损分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:query')")
    public CommonResult<WaterBalanceRespVO> getWaterBalance(@RequestParam("id") Long id) {
        WaterBalanceDO waterBalance = waterBalanceService.getWaterBalance(id);
        return success(BeanUtils.toBean(waterBalance, WaterBalanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得水量平衡与漏损分析分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:query')")
    public CommonResult<PageResult<WaterBalanceRespVO>> getWaterBalancePage(@Valid WaterBalancePageReqVO pageReqVO) {
        PageResult<WaterBalanceDO> pageResult = waterBalanceService.getWaterBalancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterBalanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出水量平衡与漏损分析 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-balance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterBalanceExcel(@Valid WaterBalancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterBalanceDO> list = waterBalanceService.getWaterBalancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "水量平衡与漏损分析.xls", "数据", WaterBalanceRespVO.class,
                        BeanUtils.toBean(list, WaterBalanceRespVO.class));
    }

}