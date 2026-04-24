package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig.CardConfigService;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder.CardOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

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

    @Resource
    private CardConfigService cardConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得卡种订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<PageResult<CardOrderRespVO>> getPage(CardOrderPageReqVO reqVO) {
        // 如果startTime和endTime都为空，且date不为空，将date转为当天开始和结束时间
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
                && reqVO.getDate() != null && !reqVO.getDate().isEmpty()) {
            java.time.LocalDate localDate = java.time.LocalDate.parse(reqVO.getDate());
            reqVO.setStartTime(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(localDate.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<CardOrderDO> pageResult = cardOrderService.getPage(reqVO);
        PageResult<CardOrderRespVO> bean = BeanUtils.toBean(pageResult, CardOrderRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得卡种订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderRespVO> get(@RequestParam("id") Long id) {
        CardOrderDO cardOrder = cardOrderService.get(id);
        CardOrderRespVO respVO = BeanUtils.toBean(cardOrder, CardOrderRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
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
        PageResult<CardOrderDO> pageResult = cardOrderService.getPage(reqVO);
        List<CardOrderRespVO> list = BeanUtils.toBean(pageResult.getList(), CardOrderRespVO.class);
        ExcelUtils.write(response, "卡种订单.xlsx", "数据", CardOrderRespVO.class, list);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public void batchExport(@RequestParam("ids") List<Long> ids, HttpServletResponse response) throws IOException {
        // TODO: 实现按ID批量查询并导出
        List<CardOrderRespVO> voList = java.util.Collections.emptyList();
        ExcelUtils.write(response, "卡种订单(批量).xlsx", "数据", CardOrderRespVO.class, voList);
    }

    @GetMapping("/chart")
    @Operation(summary = "卡种订单图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderChartRespVO> getChart() {
        return CommonResult.success(cardOrderService.getChart());
    }

    private void injectUserNames(List<CardOrderRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        Set<Long> cardIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
            if (item.getCardId() != null) {
                cardIds.add(item.getCardId());
            }
        }
        // 翻译创建者名称
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(Long.valueOf(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
            }
        }
        // 翻译卡种名称
        if (!cardIds.isEmpty()) {
            Map<Long, String> cardNameMap = new HashMap<>();
            for (Long cardId : cardIds) {
                CardConfigDO card = cardConfigService.get(cardId);
                if (card != null) cardNameMap.put(cardId, card.getName());
            }
            for (var item : list) {
                if (item.getCardId() != null) {
                    String name = cardNameMap.get(item.getCardId());
                    if (name != null) item.setCardName(name);
                }
            }
        }
    }

}
