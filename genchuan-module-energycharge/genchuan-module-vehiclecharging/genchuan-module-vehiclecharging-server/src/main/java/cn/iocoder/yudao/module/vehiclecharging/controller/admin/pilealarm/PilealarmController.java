package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import cn.iocoder.yudao.module.vehiclecharging.service.pilealarm.PilealarmService;

@Tag(name = "汽车充电 - 充电桩告警")
@RestController
@RequestMapping("/vehiclecharging/pile-alarm")
@Validated
public class PilealarmController {

    @Resource
    private PilealarmService pilealarmService;

    @PostMapping("/create")
    @Operation(summary = "创建充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:create')")
    public CommonResult<Long> createPilealarm(@Valid @RequestBody PilealarmSaveReqVO createReqVO) {
        return success(pilealarmService.createPilealarm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:update')")
    public CommonResult<Boolean> updatePilealarm(@Valid @RequestBody PilealarmSaveReqVO updateReqVO) {
        pilealarmService.updatePilealarm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充电桩告警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:delete')")
    public CommonResult<Boolean> deletePilealarm(@RequestParam("id") String id) {
        pilealarmService.deletePilealarm(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除充电桩告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:delete')")
    public CommonResult<Boolean> deletePilealarmList(@RequestParam("ids") List<String> ids) {
        pilealarmService.deletePilealarmListByIds(ids);
        return success(true);
    }
//
    @GetMapping("/get")
    @Operation(summary = "获得充电桩告警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:query')")
    public CommonResult<PilealarmRespVO> getPilealarm(@RequestParam("id") String id) {
        PilealarmDO pilealarm = pilealarmService.getPilealarm(id);
        return success(BeanUtils.toBean(pilealarm, PilealarmRespVO.class));
    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得充电桩告警分页")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:query')")
//    public CommonResult<PageResult<PilealarmRespVO>> getPilealarmPage(@Valid PilealarmPageReqVO pageReqVO) {
//        PageResult<PilealarmDO> pageResult = pilealarmService.getPilealarmPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, PilealarmRespVO.class));
//    }
//
    @GetMapping("/export-excel")
    @Operation(summary = "导出充电桩告警 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pilealarm:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPilealarmExcel(@Valid PilealarmPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PilealarmDO> list = pilealarmService.getPilealarmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充电桩告警.xls", "数据", PilealarmRespVO.class,
                BeanUtils.toBean(list, PilealarmRespVO.class));
    }
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:query')")
    @Operation(summary = "获得充电桩警告分页")
    public CommonResult<PageResult<NewPileAlarmRespVO>> page(NewPileAlarmPageReqVO reqVO) {
        return CommonResult.success(pilealarmService.page(reqVO));
    }

    @PutMapping("/dis")
    @Operation(summary = "充电桩告警 - 派单", description = "将未派单 → 已派单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:dis')")
    public CommonResult<Boolean> disPileAlarm(@Valid @RequestBody PileAlarmDisReqVO reqVO) {
        return success(pilealarmService.disPileAlarm(reqVO));
    }

    @PutMapping("/handle")
    @Operation(summary = "充电桩告警 - 处置", description = "已派单 → 处置中")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:handle')")
    public CommonResult<Boolean> handlePileAlarm(@Valid @RequestBody PileAlarmHandleReqVO reqVO) {
        return success(pilealarmService.handlePileAlarm(reqVO));
    }

    @PutMapping("/close")
    @Operation(summary = "充电桩告警 - 销单", description = "处置中 → 已销单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:close')")
    public CommonResult<Boolean> closePileAlarm(
            @RequestBody Map<String, Long> body) {
        // 直接从 JSON 里拿 id
        Long id = body.get("id");
        return success(pilealarmService.closePileAlarm(id));
    }

    @PutMapping("/remark")
    @Operation(summary = "充电桩告警 - 添加备注")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:remark')")
    public CommonResult<Boolean> updateRemark(
            @RequestBody Map<String, Object> params) {

        Long id = Long.valueOf(params.get("id").toString());
        String remark = (String) params.get("remark");
        return success(pilealarmService.updateRemark(id, remark));
    }


    @GetMapping("/chart")
    @Operation(summary = "充电桩告警处置统计图表（柱状图 + 饼图 + 卡片）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:chart')")
    public CommonResult<PileAlarmChartRespVO> getAlarmChart(PileAlarmChartReqVO reqVO) {
        return success(pilealarmService.getAlarmChart(reqVO));
    }

    @GetMapping("/chart/dailyCount")
    @Operation(summary = "每日告警数量及处置完成数量（柱状图钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:chart')")
    public CommonResult<List<PileAlarmChartRespVO.BarData>> dailyCount(PileAlarmDailyCountReqVO reqVO) {
        return success(pilealarmService.getDailyCount(reqVO));
    }

    /**
     * 告警类型占比（饼图钻取）
     */
    @GetMapping("/chart/typeRatio")
    @Operation(summary = "告警类型占比")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:chart')")
    public CommonResult<List<PileAlarmTypeRatioRespVO>> getAlarmTypeRatio(PileAlarmDailyCountReqVO reqVO) {
        List<PileAlarmTypeRatioRespVO> result = pilealarmService.getAlarmTypeRatio(reqVO);
        return CommonResult.success(result);
    }

    @GetMapping("/chart/handleCount")
    @Operation(summary = "告警处置统计")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:pile_alarm:chart')")
    public CommonResult<PileAlarmHandleCountRespVO> getHandleCount(PileAlarmDailyCountReqVO reqVO) {
        return CommonResult.success(pilealarmService.getHandleCount(reqVO));
    }

}