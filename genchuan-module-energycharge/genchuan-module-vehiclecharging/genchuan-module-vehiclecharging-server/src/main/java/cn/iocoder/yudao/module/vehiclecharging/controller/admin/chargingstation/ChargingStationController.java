package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.InterconnectionRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.service.chargingstation.ChargingStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Tag(name = "管理后台 - 充电站")
@RestController
@RequestMapping("/vehiclecharging/charging_station")
public class ChargingStationController {

    @Resource
    private ChargingStationService chargingStationService;
    /**
     * 获得充电站分页
     */
    @GetMapping("/page")
    @Operation(summary = "获得充电站分页")
    public CommonResult<PageResult<ChargingStationRespVO>> getChargingStationPage(ChargingStationPageReqVO reqVO) {
        PageResult<ChargingStationRespVO> pageResult = chargingStationService.getChargingStationPage(reqVO);
        return CommonResult.success(pageResult);
    }
    /**
     * 创建充电站场站
     */
    @PostMapping("/create")
    @Operation(summary = "创建充电站场站")
    public CommonResult<Long> createChargingStation(@Valid @RequestBody ChargingStationCreateReqVO createReqVO) {
        Long id = chargingStationService.createChargingStation(createReqVO);
        return CommonResult.success(id);
    }
    /**
     * 更新充电站场站
     */
    @PutMapping("/update")
    @Operation(summary = "更新充电站场站")
    public CommonResult<Boolean> updateChargingStation(@Valid @RequestBody ChargingStationUpdateReqVO updateReqVO) {
        chargingStationService.updateChargingStation(updateReqVO);
        return CommonResult.success(true);
    }

    /**
     * 获取充电站场站详情
     */
    @GetMapping("/get")
    @Operation(summary = "获得充电站场站详情")
    public CommonResult<ChargingStationRespVO> getChargingStation(@RequestParam("id") Long id) {
        ChargingStationRespVO chargingStation = chargingStationService.getChargingStation(id);
        return CommonResult.success(chargingStation);
    }
    /**
     * 停用充电站
     */
    @Operation(summary = "停用充电站", description = "请求体示例：{\"id\": 1001, \"stopReason\": \"设备升级改造\"}")
    @PutMapping("/disable")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:disable')")
    public CommonResult<Void> disableChargingStation(@RequestBody Map<String, Object> params) {
        Long id = Long.parseLong(params.get("id").toString());
        String stopReason = (String) params.get("stopReason");

        chargingStationService.disable(id, stopReason);
        return CommonResult.success(null);
    }
    /**
     * 启用充电站
     */
    @Operation(summary = "启用充电站", description = "请求体：{\"id\": 1001}")
    @PutMapping("/enable")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:enable')")
    public CommonResult<Void> enableChargingStation(@RequestBody Map<String, Object> params) {
        Long id = Long.parseLong(params.get("id").toString());
        chargingStationService.enable(id);
        return CommonResult.success(null);
    }
    /**
     * 批量停用充电站
     */
    @Operation(summary = "批量停用充电站")
    @PutMapping("/batch-disable")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:disable')")
    public CommonResult<Boolean> disableChargingStation(@Valid @RequestBody ChargingStationDisableReqVO reqVO) {
        chargingStationService.batchDisable(reqVO);
        return CommonResult.success(true);
    }
    /**
     * 批量启用充电站
     */
    @Operation(summary = "批量启用充电站")
    @PutMapping("/batch-enable")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:enable')")
    public CommonResult<Void> enableChargingStation(@Valid @RequestBody ChargingStationEnableReqVO reqVO) {
        chargingStationService.batchEnable(reqVO);
        return CommonResult.success(null);
    }

    @Operation(summary = "导出充电站")
    @GetMapping("/export")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:export')")
    public void exportChargingStation(ChargingStationExportReqVO exportReqVO,
                                      HttpServletResponse response) throws IOException {
        // 1. 分页查询数据
        PageResult<ChargingStationDO> pageResult = chargingStationService.getChargingStationPage(exportReqVO);
        List<ChargingStationDO> list = pageResult.getList();

        // 2. 导出 Excel
        ExcelUtils.write(response, "充电站信息.xlsx","数据", ChargingStationExcelVO.class,
                BeanUtils.toBean(list, ChargingStationExcelVO.class));
    }

    /**
     * 批量改变合作模式和负责人
     */
    @Operation(summary = "批量改变合作模式和负责人")
    @PutMapping("/batch-update")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:update')")
    public CommonResult<Boolean> batchChangeCooperationModeAndLeader(
            @Valid @RequestBody ChargingStationBatchUpdateReqVO reqVO) {

        chargingStationService.batchChangeCooperationModeAndLeader(reqVO);
        return CommonResult.success(true);
    }

//    @Operation(summary = "充电场站分布及运行状态图（地图+柱状图+卡片）")
//    @GetMapping("/chart")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:query')")
//    public CommonResult<ChargingStationChartRespVO> getChargingStationChart() {
//        return CommonResult.success(chargingStationService.getChartData());
//    }
    @GetMapping("/chart")
    @Operation(summary = "充电场站分布及运行状态图（地图+柱状图+卡片）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:query')")
    public CommonResult<ChargingStationChartRespVO> getChartData(ChargingStationChartReqVO reqVO) {
        return CommonResult.success(chargingStationService.getChartData(reqVO));
    }

    @GetMapping("/chart/areaCount")
    @Operation(summary = "各区域充电场站数量统计（柱状图钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:query')")
    public CommonResult<List<ChargingStationAreaCountRespVO>> getAreaStationCount(Long id) {
        List<ChargingStationAreaCountRespVO> list = chargingStationService.getAreaStationCount(id);
        return CommonResult.success(list);
    }

    @GetMapping("/charging-station/chart/statusCount")
    @Operation(summary = "场站状态统计（卡片钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging_station:query')")
    public CommonResult<List<StationStatusCountRespVO>> getStationStatusCount() {
        return CommonResult.success(chargingStationService.getStationStatusCount());
    }
}