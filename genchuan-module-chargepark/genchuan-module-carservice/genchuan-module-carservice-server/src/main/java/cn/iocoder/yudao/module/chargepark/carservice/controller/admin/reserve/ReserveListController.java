package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListApproveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListRejectReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.StationNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.reserve.ReserveListService;
import cn.iocoder.yudao.module.stationresource.api.parking.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.parking.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "预约服务 - 预约列表")
@RestController
@RequestMapping("/carservice/reserve-list")
@Validated
public class ReserveListController {

    @Resource
    private ReserveListService reserveListService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private StationInfoApi stationInfoApi;

    @Resource
    private ParkingSpaceInfoApi parkingSpaceInfoApi;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 预约列表")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<PageResult<ReserveListRespVO>> getReserveListPage(@Valid ReserveListPageReqVO pageReqVO) {
        PageResult<ReserveListDO> pageResult = reserveListService.getReserveListPage(pageReqVO);
        PageResult<ReserveListRespVO> respPage = BeanUtils.toBean(pageResult, ReserveListRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 预约")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<ReserveListRespVO> getReserveList(@RequestParam("id") Long id) {
        ReserveListDO reserveList = reserveListService.getReserveList(id);
        ReserveListRespVO respVO = BeanUtils.toBean(reserveList, ReserveListRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 预约(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReserveList(@Valid ReserveListPageReqVO pageReqVO,
                                  @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReserveListDO> list = reserveListService.getReserveListPage(pageReqVO).getList();
        List<ReserveListRespVO> respList = BeanUtils.toBean(list, ReserveListRespVO.class);
        injectUserNames(respList);
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "预约列表.pdf", "预约台账",
                    PdfUtils.headers(
                            "id", "ID",
                            "userName", "用户",
                            "stationId", "场站",
                            "spaceId", "车位",
                            "reserveTime", "预约时间",
                            "reserveType", "类型",
                            "status", "状态",
                            "auditUserName", "审核人",
                            "auditTime", "审核时间",
                            "finishTime", "完成时间",
                            "score", "评分"),
                    respList);
        } else {
            ExcelUtils.write(response, "预约列表.xls", "数据", ReserveListRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "预约服务统计图表 - 折线图+柱状图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<ReserveListChartRespVO> getReserveListChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartReserve(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期预约量统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<PageResult<ReserveListRespVO>> drillReserveListLine(@Valid ReserveListPageReqVO pageReqVO) {
        PageResult<ReserveListDO> pageResult = reserveListService.getReserveListPage(pageReqVO);
        PageResult<ReserveListRespVO> respPage = BeanUtils.toBean(pageResult, ReserveListRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/chart-drill-bar")
    @Operation(summary = "各类型预约统计(柱状图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<PageResult<ReserveListRespVO>> drillReserveListBar(@Valid ReserveListPageReqVO pageReqVO) {
        PageResult<ReserveListDO> pageResult = reserveListService.getReserveListPage(pageReqVO);
        PageResult<ReserveListRespVO> respPage = BeanUtils.toBean(pageResult, ReserveListRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计预约数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:query')")
    public CommonResult<Map<String, Long>> countReserveListByStatus() {
        return success(serviceOpReportService.countReserveListByStatus());
    }

    // ========== 业务操作(状态机) ==========

    @PutMapping("/approve")
    @Operation(summary = "通过 - 待审核 → 已生效")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:approve')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> approveReserveList(@Valid @RequestBody ReserveListApproveReqVO reqVO) {
        ReserveListAuditReqVO auditReq = new ReserveListAuditReqVO();
        auditReq.setId(reqVO.getId());
        auditReq.setApproved(Boolean.TRUE);
        auditReq.setAuditRemark(reqVO.getAuditRemark());
        reserveListService.auditReserveList(auditReq);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回 - 待审核 → 已取消")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:reject')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> rejectReserveList(@Valid @RequestBody ReserveListRejectReqVO reqVO) {
        ReserveListAuditReqVO auditReq = new ReserveListAuditReqVO();
        auditReq.setId(reqVO.getId());
        auditReq.setApproved(Boolean.FALSE);
        auditReq.setRejectReason(reqVO.getRejectReason());
        reserveListService.auditReserveList(auditReq);
        return success(true);
    }

    @PutMapping("/batch-audit")
    @Operation(summary = "批量审核")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:batchAudit')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> batchAuditReserveList(@Valid @RequestBody ReserveListBatchAuditReqVO reqVO) {
        reserveListService.batchAuditReserveList(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消 - 已生效 → 已取消")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:cancel')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> cancelReserveList(@RequestParam("id") Long id) {
        reserveListService.cancelReserveList(id);
        return success(true);
    }

    @PutMapping("/evaluate")
    @Operation(summary = "评价 - 已完成状态")
    @PreAuthorize("@ss.hasPermission('carservice:reserve-list:evaluate')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> evaluateReserveList(@Valid @RequestBody ReserveListEvaluateReqVO reqVO) {
        reserveListService.evaluateReserveList(reqVO);
        return success(true);
    }

    private void injectUserNames(List<ReserveListRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(ReserveListRespVO::getUserId, ReserveListRespVO::setUserName),
                UserNameInjector.field(ReserveListRespVO::getAuditUserId, ReserveListRespVO::setAuditUserName));
        StationNameInjector.inject(list, stationInfoApi,
                StationNameInjector.field(ReserveListRespVO::getStationId, ReserveListRespVO::setStationName));
        injectSpaceNos(list);
    }

    private void injectSpaceNos(List<ReserveListRespVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        java.util.Set<Long> spaceIds = new java.util.HashSet<>();
        for (ReserveListRespVO v : list) {
            if (v.getSpaceId() != null) {
                spaceIds.add(v.getSpaceId());
            }
        }
        if (spaceIds.isEmpty()) {
            return;
        }
        java.util.Map<Long, ParkingSpaceInfoRespDTO> spaceMap;
        try {
            spaceMap = parkingSpaceInfoApi.getSpaceMap(spaceIds);
        } catch (Exception ex) {
            return;
        }
        if (spaceMap == null || spaceMap.isEmpty()) {
            return;
        }
        for (ReserveListRespVO v : list) {
            if (v.getSpaceId() == null) {
                continue;
            }
            ParkingSpaceInfoRespDTO s = spaceMap.get(v.getSpaceId());
            if (s != null) {
                v.setSpaceNo(s.getSpaceNo());
            }
        }
    }

}
