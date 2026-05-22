package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder.ExchangeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 兑换订单")
@RestController
@RequestMapping("/marketop/exchange-order")
public class ExchangeOrderController {

    @Resource
    private ExchangeOrderService exchangeOrderService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得兑换订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<PageResult<ExchangeOrderRespVO>> getPage(ExchangeOrderPageReqVO reqVO) {
        PageResult<ExchangeOrderRespVO> pageResult = exchangeOrderService.getPageWithJoin(reqVO);
        injectCreatorNames(pageResult.getList());
        return CommonResult.success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得兑换订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderRespVO> get(@RequestParam("id") Long id) {
        ExchangeOrderRespVO respVO = exchangeOrderService.getWithJoin(id);
        if (respVO != null) injectCreatorNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/pay")
    @Operation(summary = "支付兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:pay')")
    public CommonResult<Boolean> pay(@Valid @RequestBody ExchangeOrderPayReqVO reqVO) {
        exchangeOrderService.pay(reqVO.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/ship")
    @Operation(summary = "发货兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:deliver')")
    public CommonResult<Boolean> deliver(@Valid @RequestBody ExchangeOrderDeliverReqVO reqVO) {
        exchangeOrderService.deliver(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:cancel')")
    public CommonResult<Boolean> cancel(@Valid @RequestBody ExchangeOrderCancelReqVO reqVO) {
        exchangeOrderService.cancel(reqVO.getId());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void export(ExchangeOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ExchangeOrderRespVO> pageResult = exchangeOrderService.getPageWithJoin(reqVO);
        injectCreatorNames(pageResult.getList());
        pageResult.getList().forEach(item -> {
            item.setPayStatus(ExchangeOrderPayStatusEnum.labelOf(item.getPayStatus()));
        });
        List<ExchangeOrderExportExcelVO> exportList = BeanUtils.toBean(pageResult.getList(), ExchangeOrderExportExcelVO.class);
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderExportExcelVO.class, exportList);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void batchExport(@RequestParam(value = "ids", required = false) List<Long> ids, HttpServletResponse response) throws IOException {
        List<ExchangeOrderRespVO> voList = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            voList = exchangeOrderService.getListByIdsWithJoin(ids);
        }
        injectCreatorNames(voList);
        voList.forEach(item -> {
            item.setPayStatus(ExchangeOrderPayStatusEnum.labelOf(item.getPayStatus()));
        });
        List<ExchangeOrderExportExcelVO> exportList = BeanUtils.toBean(voList, ExchangeOrderExportExcelVO.class);
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderExportExcelVO.class, exportList);
    }

    @GetMapping("/chart")
    @Operation(summary = "兑换订单统计图表")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderChartRespVO> getChart() {
        return CommonResult.success(exchangeOrderService.getChart());
    }

    private void injectCreatorNames(List<ExchangeOrderRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> creatorIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) creatorIds.add(id);
            }
        }
        if (!creatorIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(creatorIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
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
