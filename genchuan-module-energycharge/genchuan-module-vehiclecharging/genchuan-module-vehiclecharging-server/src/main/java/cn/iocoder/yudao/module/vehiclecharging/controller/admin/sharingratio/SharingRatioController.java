package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import cn.iocoder.yudao.module.vehiclecharging.service.sharingratio.SharingRatioService;

@Tag(name = "汽车充电 - 分账比例")
@RestController
@RequestMapping("/vehiclecharging/sharing-ratio")
@Validated
public class SharingRatioController {

    @Resource
    private SharingRatioService sharingRatioService;

    @GetMapping("/page")
    @Operation(summary = "获得分账比例分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:query')")
    public CommonResult<PageResult<SharingRatioPageRespVO>> getSharingRatioPage(@Valid SharingRatioPageReqVO pageReqVO) {
        PageResult<SharingRatioDO> pageResult = sharingRatioService.getSharingRatioPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SharingRatioPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建分账比例")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:create')")
    public CommonResult<Long> createSharingRatio(@Valid @RequestBody SharingRatioCreateReqVO createReqVO) {
        return success(sharingRatioService.createSharingRatio(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分账比例")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:update')")
    public CommonResult<Boolean> updateSharingRatio(@Valid @RequestBody SharingRatioUpdateReqVO updateReqVO) {
        sharingRatioService.updateSharingRatio(updateReqVO);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效分账方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:enable')")
    public CommonResult<Boolean> enableSharingRatio(@RequestParam("id") Long id) {
        sharingRatioService.updateSharingStatus(id,0);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "失效分账方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:disable')")
    public CommonResult<Boolean> disableSharingRatio(@RequestParam("id") Long id) {
        sharingRatioService.updateSharingStatus(id,1);
        return success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出分账比例 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSharingRatioExcel(@Valid SharingRatioPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SharingRatioDO> list = sharingRatioService.getSharingRatioPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "分账比例.xls", "数据", SharingRatioRespVO.class,
                BeanUtils.toBean(list, SharingRatioRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得分账比例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:query')")
    public CommonResult<SharingRatioRespVO> getSharingRatio(@RequestParam("id") Long id) {
        SharingRatioDO sharingRatio = sharingRatioService.getSharingRatio(id);
        return success(BeanUtils.toBean(sharingRatio, SharingRatioRespVO.class));
    }

    @PostMapping("/copy")
    @Operation(summary = "复制分账方案")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-ratio:copy')")
    public CommonResult<Long> copySharingRatio(@Valid @RequestBody SharingRatioCopyReqVO reqVO) {
        return success(sharingRatioService.copySharingRatio(reqVO.getId()));
    }

    @GetMapping("/chart")
    @Operation(summary = "分账比例分布图表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_ratio:query')")
    public CommonResult<SharingRatioSummaryRespVO> getChart(@Valid SharingRatioChartReqVO reqVO) {
        return success(sharingRatioService.getChartSummary(reqVO));
    }

    @GetMapping("/chart/cooperatorRatio")
    @Operation(summary = "各合作方分账比例占比（饼图钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_ratio:query')")
    public CommonResult<SharingRatioCooperatorRatioRespVO> getCooperatorRatio(@Valid SharingRatioChartReqVO reqVO) {
        return success(sharingRatioService.getCooperatorRatio(reqVO));
    }

    @GetMapping("/chart/schemeCompare")
    @Operation(summary = "各分账方案比例对比（柱状图钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_ratio:query')")
    public CommonResult<SharingRatioSchemeCompareRespVO> schemeCompare(@RequestParam("schemeIds") List<Long> schemeIds) {
        return success(sharingRatioService.schemeCompare(schemeIds));
    }

    @GetMapping("/chart/statusCount")
    @Operation(summary = "分账方案状态统计（卡片钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_ratio:query')")
    public CommonResult<SharingRatioStatusCountRespVO> getStatusCount(@Valid SharingRatioChartReqVO reqVO) {
        return success(sharingRatioService.getStatusCount(reqVO));
    }

}