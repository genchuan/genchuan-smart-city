package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import cn.iocoder.yudao.module.vehiclecharging.service.charginglot.ChargingLotService;

@Tag(name = "管理后台 - 充电车位")
@RestController
@RequestMapping("/vehiclecharging/charging-lot")
@Validated
public class ChargingLotController {

    @Resource
    private ChargingLotService chargingLotService;

    @PostMapping("/create")
    @Operation(summary = "创建充电车位")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:create')")
    public CommonResult<Long> createChargingLot(@Valid @RequestBody ChargingLotSaveReqVO createReqVO) {
        return success(chargingLotService.createChargingLot(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电车位")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:update')")
    public CommonResult<Boolean> updateChargingLot(@Valid @RequestBody ChargingLotSaveReqVO updateReqVO) {
        chargingLotService.updateChargingLot(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电车位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:delete')")
    public CommonResult<Boolean> deleteChargingLot(@RequestParam("id") Long id) {
        chargingLotService.deleteChargingLot(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除充电车位")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:delete')")
    public CommonResult<Boolean> deleteChargingLotList(@RequestParam("ids") List<Long> ids) {
        chargingLotService.deleteChargingLotListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充电车位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:query')")
    public CommonResult<ChargingLotRespVO> getChargingLot(@RequestParam("id") Long id) {
        ChargingLotDO chargingLot = chargingLotService.getChargingLot(id);
        return success(BeanUtils.toBean(chargingLot, ChargingLotRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充电车位分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:query')")
    public CommonResult<PageResult<ChargingLotRespVO>> getChargingLotPage(@Valid ChargingLotPageReqVO pageReqVO) {
        PageResult<ChargingLotDO> pageResult = chargingLotService.getChargingLotPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChargingLotRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充电车位 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChargingLotExcel(@Valid ChargingLotPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChargingLotDO> list = chargingLotService.getChargingLotPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电车位.xls", "数据", ChargingLotRespVO.class,
                        BeanUtils.toBean(list, ChargingLotRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "获得充电车位图表统计数据（用于数据可视化大屏）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-lot:query')")
    public CommonResult<ChargingLotChartRespVO> getChargingLotChart() {
        return success(chargingLotService.getChargingLotChart());
    }


}