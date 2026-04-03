package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile;

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
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.LotSimpleRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.StationSimpleRespVO;
import cn.iocoder.yudao.module.vehiclecharging.service.pile.PileService;

@Tag(name = "管理后台 - 充电桩")
@RestController
@RequestMapping("/vehiclecharging/charging-pile")
@Validated
public class PileController {

    @Resource
    private PileService pileService;

    @PostMapping("/create")
    @Operation(summary = "创建充电桩")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:create')")
    public CommonResult<Long> createPile(@Valid @RequestBody PileSaveReqVO createReqVO) {
        return success(pileService.createPile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电桩")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:update')")
    public CommonResult<Boolean> updatePile(@Valid @RequestBody PileSaveReqVO updateReqVO) {
        pileService.updatePile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电桩")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:delete')")
    public CommonResult<Boolean> deletePile(@RequestParam("id") Long id) {
        pileService.deletePile(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除充电桩")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:delete')")
    public CommonResult<Boolean> deletePileList(@RequestParam("ids") List<Long> ids) {
        pileService.deletePileListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充电桩")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<PileRespVO> getPile(@RequestParam("id") Long id) {
        return success(pileService.getPile(id));
    }

    @GetMapping("/status-dict")
    @Operation(summary = "获取充电桩状态字典")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<PileStatusDictRespVO>> getPileStatusDictList() {
        return success(pileService.getPileStatusDictList());
    }

    @GetMapping("/charge-mode-dict")
    @Operation(summary = "获取充电模式字典")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<PileStatusDictRespVO>> getChargeModeDictList() {
        return success(pileService.getChargeModeDictList());
    }

    @GetMapping("/qrcode")
    @Operation(summary = "获取充电枪二维码")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public void getPileQrcode(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        byte[] qrcodeBytes = pileService.getPileQrcode(id);
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline; filename=\"qrcode.png\"");
        response.getOutputStream().write(qrcodeBytes);
        response.getOutputStream().flush();
    }

    @GetMapping("/page")
    @Operation(summary = "获得充电桩分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<PageResult<PileRespVO>> getPilePage(@Valid PilePageReqVO pageReqVO) {
        return success(pileService.getPilePage(pageReqVO));
    }

    @PostMapping("/debug")
    @Operation(summary = "调试充电桩")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:update')")
    public CommonResult<Boolean> debugPile(@Valid @RequestBody PileDebugReqVO reqVO) {
        pileService.debugPile(reqVO.getId());
        return success(true);
    }

    @PostMapping("/enable")
    @Operation(summary = "启用充电桩")
    @ApiAccessLog(operateType = OTHER)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:update')")
    public CommonResult<Boolean> enablePile(@Valid @RequestBody PileEnableReqVO reqVO) {
        pileService.enablePile(reqVO.getId());
        return success(true);
    }

    @PostMapping("/disable")
    @Operation(summary = "停用充电桩")
    @ApiAccessLog(operateType = OTHER)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:update')")
    public CommonResult<Boolean> disablePile(@Valid @RequestBody PileDisableReqVO reqVO) {
        pileService.disablePile(reqVO.getId(), reqVO.getRemark());
        return success(true);
    }

    @PostMapping("/restart")
    @Operation(summary = "远程重启充电桩")
    @ApiAccessLog(operateType = OTHER)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:update')")
    public CommonResult<Boolean> restartPile(@Valid @RequestBody PileRestartReqVO reqVO) {
        return success(pileService.restartPile(reqVO.getId()));
    }

    @GetMapping("/chart")
    @Operation(summary = "获取充电桩图表统计")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<PileChartRespVO> getPileChart(@Valid PileChartReqVO reqVO) {
        return success(pileService.getPileChart(reqVO));
    }

    @GetMapping("/runTimeTrend")
    @Operation(summary = "获取充电桩运行时长趋势")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<PileRunTimeTrendRespVO>> getRunTimeTrendList(@Valid PileRunTimeTrendReqVO reqVO) {
        return success(pileService.getRunTimeTrendList(reqVO));
    }

    @GetMapping("/typeCount")
    @Operation(summary = "获取充电桩充电模式统计")
    @Parameter(name = "stationId", description = "场站ID", example = "27260")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<PileChargeModeStatRespVO>> getChargeModeStatList(
            @RequestParam(value = "stationId", required = false) Long stationId) {
        return success(pileService.getChargeModeStatList(stationId));
    }

    @GetMapping("/statusCount")
    @Operation(summary = "获取充电桩状态统计")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<PileStatusStatRespVO>> getPileStatusStatList() {
        return success(pileService.getPileStatusStatList());
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充电桩 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPileExcel(@Valid PilePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PileRespVO> list = pileService.getPilePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电桩.xls", "数据", PileRespVO.class, list);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取充电车位简易列表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<LotSimpleRespVO>> getLotSimpleList() {
        return success(pileService.getLotSimpleList());
    }

    @GetMapping("/station-simple-list")
    @Operation(summary = "获取场站简易列表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:charging-pile:query')")
    public CommonResult<List<StationSimpleRespVO>> getStationSimpleList() {
        return success(pileService.getStationSimpleList());
    }

}