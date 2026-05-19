package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ReceiveRecordStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ReceiveRecordSyncStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt.CouponMgmtService;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.receiverecord.ReceiveRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 领用记录")
@RestController
@RequestMapping("/marketop/receive-record")
public class ReceiveRecordController {

    @Resource
    private ReceiveRecordService receiveRecordService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private CouponMgmtService couponMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得领用记录分页")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<PageResult<ReceiveRecordRespVO>> getPage(ReceiveRecordPageReqVO reqVO) {
//        // 如果startTime和endTime都为空，且date不为空，将date转为当天开始和结束时间
//        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
//                && reqVO.getDate() != null && !reqVO.getDate().isEmpty()) {
//            java.time.LocalDate localDate = java.time.LocalDate.parse(reqVO.getDate());
//            reqVO.setStartTime(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
//            reqVO.setEndTime(localDate.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
//        }
        PageResult<ReceiveRecordDO> pageResult = receiveRecordService.getPage(reqVO);
        PageResult<ReceiveRecordRespVO> bean = BeanUtils.toBean(pageResult, ReceiveRecordRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得领用记录详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<ReceiveRecordRespVO> get(@RequestParam("id") Long id) {
        ReceiveRecordDO receiveRecord = receiveRecordService.get(id);
        ReceiveRecordRespVO respVO = BeanUtils.toBean(receiveRecord, ReceiveRecordRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/check")
    @Operation(summary = "核查领用记录")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<Boolean> check(@Valid @RequestBody ReceiveRecordCheckReqVO reqVO) {
        receiveRecordService.check(reqVO.getId(), reqVO.getCheckResult());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出领用记录")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public void export(ReceiveRecordPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ReceiveRecordDO> pageResult = receiveRecordService.getPage(reqVO);
        List<ReceiveRecordRespVO> list = BeanUtils.toBean(pageResult.getList(), ReceiveRecordRespVO.class);
        injectUserNames(list);
        list.forEach(item -> {
            item.setStatus(ReceiveRecordStatusEnum.labelOf(item.getStatus()));
            item.setSyncStatus(ReceiveRecordSyncStatusEnum.labelOf(item.getSyncStatus()));
        });
        List<ReceiveRecordExportExcelVO> exportList = BeanUtils.toBean(list, ReceiveRecordExportExcelVO.class);
        ExcelUtils.write(response, "领用记录.xlsx", "数据", ReceiveRecordExportExcelVO.class, exportList);
    }

    @GetMapping("/chart")
    @Operation(summary = "领用记录图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<ReceiveRecordChartRespVO> getChart() {
        return CommonResult.success(receiveRecordService.getChart());
    }

    private void injectUserNames(List<ReceiveRecordRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        Set<Long> couponIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
            if (item.getUserId() != null) {
                userIds.add(item.getUserId());
            }
            if (item.getCouponId() != null) {
                couponIds.add(item.getCouponId());
            }
        }
        // 翻译用户名称
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
                if (item.getUserId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getUserId());
                    if (user != null) item.setUserName(user.getNickname());
                }
            }
        }
        // 翻译优惠券名称
        if (!couponIds.isEmpty()) {
            Map<Long, String> couponNameMap = new HashMap<>();
            for (Long couponId : couponIds) {
                CouponMgmtDO coupon = couponMgmtService.get(couponId);
                if (coupon != null) couponNameMap.put(couponId, coupon.getName());
            }
            for (var item : list) {
                if (item.getCouponId() != null) {
                    String name = couponNameMap.get(item.getCouponId());
                    if (name != null) item.setCouponName(name);
                }
            }
        }
    }

    private Long safeParseLong(String s) {
        if (s == null) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
