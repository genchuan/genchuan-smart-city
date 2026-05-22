package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointLotteryStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointLotterySyncStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointlottery.PointLotteryService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 积分抽奖")
@RestController
@RequestMapping("/marketop/point-lottery")
public class PointLotteryController {

    @Resource
    private PointLotteryService pointLotteryService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得积分抽奖分页")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PageResult<PointLotteryRespVO>> getPage(PointLotteryPageReqVO reqVO) {
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
                && reqVO.getLotteryTime() != null && !reqVO.getLotteryTime().isEmpty()) {
            java.time.LocalDate date = java.time.LocalDate.parse(reqVO.getLotteryTime());
            reqVO.setStartTime(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(date.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<PointLotteryRespVO> pageResult = pointLotteryService.getPageWithJoin(reqVO);
        injectSenderNames(pageResult.getList());
        return CommonResult.success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分抽奖详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PointLotteryRespVO> get(@RequestParam("id") Long id) {
        PointLotteryRespVO respVO = pointLotteryService.getWithJoin(id);
        if (respVO != null) injectSenderNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/check")
    @Operation(summary = "核查积分抽奖记录")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<Boolean> check(@Valid @RequestBody PointLotteryCheckReqVO reqVO) {
        pointLotteryService.check(reqVO.getId(), reqVO.getCheckResult());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出积分抽奖记录")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public void export(PointLotteryPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PointLotteryRespVO> pageResult = pointLotteryService.getPageWithJoin(reqVO);
        injectSenderNames(pageResult.getList());
        pageResult.getList().forEach(item -> {
            item.setStatus(PointLotteryStatusEnum.labelOf(item.getStatus()));
            item.setSyncStatus(PointLotterySyncStatusEnum.labelOf(item.getSyncStatus()));
        });
        List<PointLotteryExportExcelVO> exportList = BeanUtils.toBean(pageResult.getList(), PointLotteryExportExcelVO.class);
        ExcelUtils.write(response, "积分抽奖.xlsx", "数据", PointLotteryExportExcelVO.class, exportList);
    }

    @GetMapping("/chart")
    @Operation(summary = "积分抽奖图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PointLotteryChartRespVO> getChart() {
        return CommonResult.success(pointLotteryService.getChart());
    }

    private void injectSenderNames(List<PointLotteryRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> senderIds = new HashSet<>();
        for (var item : list) {
            if (item.getSenderId() != null) senderIds.add(item.getSenderId());
        }
        if (!senderIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(senderIds);
            for (var item : list) {
                if (item.getSenderId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getSenderId());
                    if (user != null) item.setSenderName(user.getNickname());
                }
            }
        }
    }

}
