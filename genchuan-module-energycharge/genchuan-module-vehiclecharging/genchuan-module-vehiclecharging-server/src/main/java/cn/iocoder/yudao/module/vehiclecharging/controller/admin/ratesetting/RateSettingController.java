package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.ratesetting.RateSettingDO;
import cn.iocoder.yudao.module.vehiclecharging.service.ratesetting.RateSettingService;
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


@Tag(name = "汽车充电 - 费率设置")
@RestController
@RequestMapping("/vehiclecharging/rate-setting")
@Validated
public class RateSettingController {

    @Resource
    private RateSettingService rateSettingService;

    // ==================== 新增：费率设置图表统计接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "费率设置分布图表（柱状图 + 卡片）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:query')")
    public CommonResult<RateSettingChartRespVO> getRateSettingChart(@Valid RateSettingChartReqVO reqVO) {
        RateSettingChartRespVO respVO = rateSettingService.getRateSettingChart(reqVO);
        return success(respVO);
    }
//
    @GetMapping("/chart/gradeCount")
    @Operation(summary = "各费率档次数量统计（柱状图钻取）-通过【费率档案名称】获取分组数量")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:query')")
    public CommonResult<List<RateSettingGradeCountRespVO>> getRateSettingGradeCount(@Valid RateSettingGradeCountReqVO reqVO) {
        List<RateSettingGradeCountRespVO> respVO = rateSettingService.getRateSettingGradeCount(reqVO);
        return success(respVO);
    }


    @GetMapping("/chart/statusCount")
    @Operation(summary = "费率状态统计（卡片钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:query')")
    public CommonResult<RateSettingStatusCountRespVO> getRateSettingStatusCount(@Valid RateSettingStatusCountReqVO reqVO) {
        RateSettingStatusCountRespVO respVO = rateSettingService.getRateSettingStatusCount(reqVO);
        return success(respVO);
    }
    // ==================== 新增：复制费率方案接口 ====================

    @PostMapping("/copy")
    @Operation(summary = "复制费率方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:copy')")
    public CommonResult<Boolean> copyRateSetting(@Valid @RequestBody RateSettingCopyReqVO reqVO) {
        rateSettingService.copyRateSetting(reqVO);
        return success(true);
    }
    // ==================== 以下是新增：生效 / 失效接口 ====================

    @PutMapping("/enable")
    @Operation(summary = "生效费率方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:enable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> enableRateSetting(@Valid @RequestBody RateSettingEnableReqVO reqVO) {
        rateSettingService.enableRateSetting(reqVO);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "失效费率方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate_setting:disable')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> disableRateSetting(@Valid @RequestBody RateSettingDisableReqVO reqVO) {
        rateSettingService.disableRateSetting(reqVO);
        return success(true);
    }
    @PostMapping("/create")
    @Operation(summary = "创建费率设置")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:create')")
    public CommonResult<Long> createRateSetting(@Valid @RequestBody RateSettingSaveReqVO createReqVO) {
        return success(rateSettingService.createRateSetting(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新费率设置")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:update')")
    public CommonResult<Boolean> updateRateSetting(@Valid @RequestBody RateSettingSaveReqVO updateReqVO) {
        rateSettingService.updateRateSetting(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除费率设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:delete')")
    public CommonResult<Boolean> deleteRateSetting(@RequestParam("id") Long id) {
        rateSettingService.deleteRateSetting(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除费率设置")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:delete')")
    public CommonResult<Boolean> deleteRateSettingList(@RequestParam("ids") List<Long> ids) {
        rateSettingService.deleteRateSettingListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得费率设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:query')")
    public CommonResult<RateSettingRespVO> getRateSetting(@RequestParam("id") Long id) {
        RateSettingDO rateSetting = rateSettingService.getRateSetting(id);
        return success(BeanUtils.toBean(rateSetting, RateSettingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得费率设置分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:query')")
    public CommonResult<PageResult<RateSettingRespVO>> getRateSettingPage(@Valid RateSettingPageReqVO pageReqVO) {
        PageResult<RateSettingRespVO> pageResult = rateSettingService.getRateSettingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RateSettingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出费率设置 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:rate-setting:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRateSettingExcel(@Valid RateSettingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(9999);
        List<RateSettingRespVO> list = rateSettingService.getRateSettingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "费率设置.xls", "数据", RateSettingRespVO.class,
                        BeanUtils.toBean(list, RateSettingRespVO.class));
    }

}
