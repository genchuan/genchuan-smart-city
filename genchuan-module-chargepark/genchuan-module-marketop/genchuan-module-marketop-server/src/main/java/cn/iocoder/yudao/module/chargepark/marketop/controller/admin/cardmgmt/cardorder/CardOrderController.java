package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder.CardOrderService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 卡种订单")
@RestController
@RequestMapping("/marketop/card-order")
public class CardOrderController {

    @Resource
    private CardOrderService cardOrderService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得卡种订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<PageResult<CardOrderRespVO>> getPage(CardOrderPageReqVO reqVO) {
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
                && reqVO.getDate() != null && !reqVO.getDate().isEmpty()) {
            java.time.LocalDate localDate = java.time.LocalDate.parse(reqVO.getDate());
            reqVO.setStartTime(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(localDate.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<CardOrderRespVO> pageResult = cardOrderService.getPageWithJoin(reqVO);
        injectCreatorNames(pageResult.getList());
        return CommonResult.success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得卡种订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderRespVO> get(@RequestParam("id") Long id) {
        CardOrderRespVO respVO = cardOrderService.getWithJoin(id);
        if (respVO != null) {
            injectCreatorNames(Collections.singletonList(respVO));
        }
        return CommonResult.success(respVO);
    }

    @PutMapping("/pay")
    @Operation(summary = "支付卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> pay(@RequestParam("id") Long id) {
        cardOrderService.pay(id);
        return CommonResult.success(true);
    }

    @PutMapping("/activate")
    @Operation(summary = "激活卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> activate(@RequestBody CardOrderActivateReqVO reqVO) {
        cardOrderService.activate(reqVO.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/invoice")
    @Operation(summary = "开票卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> invoice(@RequestBody CardOrderInvoiceReqVO reqVO) {
        cardOrderService.invoice(reqVO.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> cancel(@RequestBody CardOrderCancelReqVO reqVO) {
        cardOrderService.cancel(reqVO.getId());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public void export(CardOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<CardOrderRespVO> pageResult = cardOrderService.getPageWithJoin(reqVO);
        injectCreatorNames(pageResult.getList());
        List<CardOrderExportExcelVO> exportList = BeanUtils.toBean(pageResult.getList(), CardOrderExportExcelVO.class);
        ExcelUtils.write(response, "卡种订单.xlsx", "数据", CardOrderExportExcelVO.class, exportList);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public void batchExport(@RequestParam("ids") List<Long> ids, HttpServletResponse response) throws IOException {
        List<CardOrderRespVO> list = cardOrderService.getListByIdsWithJoin(ids);
        injectCreatorNames(list);
        List<CardOrderExportExcelVO> exportList = BeanUtils.toBean(list, CardOrderExportExcelVO.class);
        ExcelUtils.write(response, "卡种订单(批量).xlsx", "数据", CardOrderExportExcelVO.class, exportList);
    }

    @GetMapping("/chart")
    @Operation(summary = "卡种订单图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderChartRespVO> getChart() {
        return CommonResult.success(cardOrderService.getChart());
    }

    private void injectCreatorNames(List<CardOrderRespVO> list) {
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
